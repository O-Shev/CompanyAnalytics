package com.leshkins.companyanalytics.company_service.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateAddressDto(
        @NotNull(message = "country should be present")
        @NotBlank(message = "country should not be blank")
        String country,

        @NotNull(message = "city should be present")
        @NotBlank(message = "city should not be blank")
        String city,

        @NotNull(message = "street should be present")
        @NotBlank(message = "street should not be blank")
        String street,

        @NotNull(message = "zip should be present")
        @NotBlank(message = "zip should not be blank")
        String zip,

        List<String> categories
) {}
