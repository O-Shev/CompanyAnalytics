package com.leshkins.companyanalytics.company_service.controller;

import com.leshkins.companyanalytics.company_service.dto.address.AddressDto;
import com.leshkins.companyanalytics.company_service.dto.address.CreateAddressDto;
import com.leshkins.companyanalytics.company_service.dto.company.CompanyDto;
import com.leshkins.companyanalytics.company_service.dto.company.CreateCompanyDto;
import com.leshkins.companyanalytics.company_service.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
@Validated
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public AddressDto createAddress(@RequestBody @Valid CreateAddressDto createAddressDto) {
        return addressService.createAddress(createAddressDto);
    }

    @GetMapping
    public List<AddressDto> getCompanies(@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        return addressService.getAllAddresses(page);
    }
}
