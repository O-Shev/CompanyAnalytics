package com.leshkins.companyanalytics.company_service.dto.address;

import lombok.Builder;

import java.util.List;

@Builder
public record AddressDto(
        Long id,
        String country,
        String city,
        String street,
        String zip,
        List<String> categories
) {}
