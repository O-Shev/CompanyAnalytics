package com.leshkins.companyanalytics.analyticsservice.event;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyEvent {
    private Company before;
    private Company after;
    private String op;
    private Long tsMs;

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Company {
        private Long id;
        private String name;
        private String status;
        private String registrationNumber;
        private String contactInfo;
        private String industry;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
    }
}
