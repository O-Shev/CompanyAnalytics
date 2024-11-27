package com.leshkins.companyanalytics.company_service.service;

import com.leshkins.companyanalytics.company_service.dto.address.AddressDto;
import com.leshkins.companyanalytics.company_service.dto.address.CreateAddressDto;
import com.leshkins.companyanalytics.company_service.dto.company.CompanyDto;
import com.leshkins.companyanalytics.company_service.exception.NotFoundException;
import com.leshkins.companyanalytics.company_service.mapper.AddressMapper;
import com.leshkins.companyanalytics.company_service.model.entity.Address;
import com.leshkins.companyanalytics.company_service.model.entity.AddressCategory;
import com.leshkins.companyanalytics.company_service.repository.AddressCategoryRepository;
import com.leshkins.companyanalytics.company_service.repository.AddressRepository;
import com.leshkins.companyanalytics.company_service.validator.AddressValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressValidator addressValidator;
    private final AddressMapper addressMapper;
    private final AddressCategoryRepository addressCategoryRepository;



    @Transactional
    public AddressDto createAddress(CreateAddressDto createAddressDto) {
        addressValidator.validateCreateAddress(createAddressDto);
        Address address = addressRepository.save(addressMapper.toModel(createAddressDto));
        address.setCategories(getOrInsertAddressesCategory(createAddressDto.categories()));
        return addressMapper.toDto(address);
    }

    public List<AddressDto> getAllAddresses(int page) {
        int pageSize = 5;
        return addressRepository.findAll(PageRequest.of(page, pageSize)).stream().map(addressMapper::toDto).toList();
    }

    public Address getAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()) throw new NotFoundException("Address not found");
        return address.get();
    }

    @Transactional
    public List<AddressCategory> getOrInsertAddressesCategory(List<String> categories) {
        // Find existing categories
        List<AddressCategory> existingCategories = addressCategoryRepository.findAllByNameIn(categories);

        // Extract names of existing categories
        List<String> existingCategoryNames = existingCategories.stream()
                .map(AddressCategory::getName)
                .toList();

        // Filter categories that are not yet existing
        List<String> newCategoryNames = categories.stream()
                .filter(category -> !existingCategoryNames.contains(category))
                .toList();

        // Create new categories
        List<AddressCategory> newCategories = newCategoryNames.stream()
                .map(categoryName -> {
                    AddressCategory addressCategory = new AddressCategory();
                    addressCategory.setName(categoryName);
                    return addressCategory;
                })
                .toList();

        // Save new categories to the database
        addressCategoryRepository.saveAll(newCategories);

        // Return the combined list of existing and new categories
        existingCategories.addAll(newCategories);
        return existingCategories;
    }
}
