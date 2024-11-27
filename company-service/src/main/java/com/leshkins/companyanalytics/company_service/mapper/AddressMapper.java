package com.leshkins.companyanalytics.company_service.mapper;

import com.leshkins.companyanalytics.company_service.dto.address.AddressDto;

import com.leshkins.companyanalytics.company_service.dto.address.CreateAddressDto;
import com.leshkins.companyanalytics.company_service.model.entity.Address;
import com.leshkins.companyanalytics.company_service.model.entity.AddressCategory;
import com.leshkins.companyanalytics.company_service.service.AddressService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target="categories", ignore = true)
    Address toModel(CreateAddressDto createAddressDto);

    @Mapping(target = "categories", expression="java(mapCategories(address))")
    AddressDto toDto(Address address);

    default List<String> mapCategories(Address address){
        return address.getCategories().stream().map(AddressCategory::getName).toList();
    }
}
