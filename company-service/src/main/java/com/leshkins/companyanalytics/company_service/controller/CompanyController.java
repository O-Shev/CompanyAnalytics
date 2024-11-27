package com.leshkins.companyanalytics.company_service.controller;

import com.leshkins.companyanalytics.company_service.dto.company.CompanyDto;
import com.leshkins.companyanalytics.company_service.dto.company.CreateCompanyDto;
import com.leshkins.companyanalytics.company_service.dto.company.UpdateCompanyDto;
import com.leshkins.companyanalytics.company_service.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
@Validated
public class CompanyController {

    private final CompanyService companyService;

    // Create a new company
    @PostMapping
    public CompanyDto createCompany(@RequestBody @Valid CreateCompanyDto createCompanyDto) {
        return companyService.createCompany(createCompanyDto);
    }

    // View all companies
    @GetMapping
    public List<CompanyDto> getCompanies(@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        return companyService.getAllCompanies(page);
    }

    // View a specific company by ID
    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    // Update a company
    @PostMapping("/update/{id}")
    public CompanyDto updateCompany(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCompanyDto updateCompanyDto) {
        return companyService.updateCompany(id, updateCompanyDto);
    }

    // Delete a company
    @PostMapping("/delete/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }

    @PostMapping("/addAddress/{company_id}")
    public void addAddress(@PathVariable Long company_id, @RequestParam("address_id") Long addressId) {
        companyService.addAddress(company_id, addressId);
    }
}

