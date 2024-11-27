package com.leshkins.companyanalytics.company_service.mapper;

import com.leshkins.companyanalytics.company_service.dto.address.AddressDto;
import com.leshkins.companyanalytics.company_service.dto.company.CompanyDto;
import com.leshkins.companyanalytics.company_service.dto.company.CreateCompanyDto;
import com.leshkins.companyanalytics.company_service.model.entity.Address;
import com.leshkins.companyanalytics.company_service.model.entity.AddressCategory;
import com.leshkins.companyanalytics.company_service.model.entity.Company;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring", uses = {AddressMapper.class})
public abstract class CompanyMapper {
    @Autowired
    protected AddressMapper addressMapper;

    public abstract Company toModel(CreateCompanyDto companyDto);

    @Mapping(target = "addresses", expression = "java(mapAddresses(company))")
    public abstract CompanyDto toDto(Company company);

    public List<AddressDto> mapAddresses(Company company){
        if(company.getAddresses()==null) return Collections.emptyList();
        return company.getAddresses().stream().map((a)->addressMapper.toDto(a)).toList();
    }
}
