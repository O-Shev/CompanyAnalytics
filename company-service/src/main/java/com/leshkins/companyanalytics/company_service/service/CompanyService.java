package com.leshkins.companyanalytics.company_service.service;

import com.leshkins.companyanalytics.company_service.dto.company.CompanyDto;
import com.leshkins.companyanalytics.company_service.dto.company.CreateCompanyDto;
import com.leshkins.companyanalytics.company_service.dto.company.UpdateCompanyDto;
import com.leshkins.companyanalytics.company_service.exception.NotFoundException;
import com.leshkins.companyanalytics.company_service.mapper.CompanyMapper;
import com.leshkins.companyanalytics.company_service.model.entity.Address;
import com.leshkins.companyanalytics.company_service.model.entity.Company;
import com.leshkins.companyanalytics.company_service.model.enums.CompanyStatus;
import com.leshkins.companyanalytics.company_service.repository.CompanyRepository;
import com.leshkins.companyanalytics.company_service.validator.CompanyValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyValidator companyValidator;
    private final AddressService addressService;

    @Transactional
    public CompanyDto createCompany(CreateCompanyDto createCompanyDto) {
        System.out.println(createCompanyDto);
        Company company = companyMapper.toModel(createCompanyDto);
        company.setStatus(CompanyStatus.ACTIVE);
        System.out.println(company);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Transactional(readOnly = true)
    public List<CompanyDto> getAllCompanies(int page) {
        int pageSize = 10;
        return companyRepository.findAll(PageRequest.of(page, pageSize))
                .stream()
                .map(companyMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public CompanyDto getCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isEmpty()) throw new NotFoundException("Company not found");
        return companyMapper.toDto(companyOptional.get());
    }

    @Transactional
    public CompanyDto updateCompany(Long id, UpdateCompanyDto updateCompanyDto) {
        Company company = companyValidator.validateOnExist(id, updateCompanyDto);

        company.setName(updateCompanyDto.name());
        company.setContactInfo(updateCompanyDto.contactInfo());

        return companyMapper.toDto(companyRepository.save(company));
    }

    @Transactional
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Transactional
    public void addAddress(Long companyId, Long addressId) {
        Address address = addressService.getAddress(addressId);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found"));

        company.getAddresses().add(address);
        companyRepository.save(company);
    }


    public void aggregateAddressCategoryStats(Long id){

    }
}
