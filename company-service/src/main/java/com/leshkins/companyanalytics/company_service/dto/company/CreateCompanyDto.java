package com.leshkins.companyanalytics.company_service.dto.company;


import com.leshkins.companyanalytics.company_service.validator.industry.ValidIndustry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCompanyDto(
        @NotNull(message = "name should be present")
        @NotBlank(message = "content should not be blank")
        @Size(min = 6, max = 255, message = "name must be at least 6 characters long and not greater 255")
        String name,

        String contactInfo,

        @NotBlank(message = "Industry must not be blank")
        @ValidIndustry
        String industry
) {}
