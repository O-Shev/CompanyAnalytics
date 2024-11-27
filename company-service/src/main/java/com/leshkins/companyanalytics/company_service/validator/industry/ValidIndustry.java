package com.leshkins.companyanalytics.company_service.validator.industry;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IndustryValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIndustry {

    String message() default "Invalid industry. Must be one of the predefined enum values.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
