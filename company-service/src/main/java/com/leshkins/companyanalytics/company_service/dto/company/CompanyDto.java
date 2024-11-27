package com.leshkins.companyanalytics.company_service.dto.company;


import com.leshkins.companyanalytics.company_service.dto.address.AddressDto;
import com.leshkins.companyanalytics.company_service.model.entity.Address;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


@Builder
public record CompanyDto(
    Long id,
    String name,
    String status, // Enum as String
    String registrationNumber,
    String contactInfo,
    String industry, // Enum as String
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<AddressDto> addresses
) {}
