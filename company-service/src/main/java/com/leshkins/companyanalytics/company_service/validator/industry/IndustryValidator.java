package com.leshkins.companyanalytics.company_service.validator.industry;

import com.leshkins.companyanalytics.company_service.model.enums.Industry;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IndustryValidator implements ConstraintValidator<ValidIndustry, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        try {
            Industry.valueOf(value); // Check if the value exists in the enum
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
