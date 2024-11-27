package com.leshkins.companyanalytics.company_service.validator;

import com.leshkins.companyanalytics.company_service.dto.company.CreateCompanyDto;
import com.leshkins.companyanalytics.company_service.dto.company.UpdateCompanyDto;
import com.leshkins.companyanalytics.company_service.exception.NotFoundException;
import com.leshkins.companyanalytics.company_service.model.entity.Company;
import com.leshkins.companyanalytics.company_service.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyValidator {
    private final CompanyRepository companyRepository;

    public void validateCreation(CreateCompanyDto createCompanyDto) {
        //here can be some logic
    }

    public Company validateOnExist(Long id, UpdateCompanyDto updateCompanyDto) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) throw new NotFoundException("Company not found");
        return company.get();
    }
}
