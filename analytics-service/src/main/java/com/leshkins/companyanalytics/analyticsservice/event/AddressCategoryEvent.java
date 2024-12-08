package com.leshkins.companyanalytics.analyticsservice.event;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressCategoryEvent {
    private NameData before;
    private NameData after;
    private String op;
    private Long tsMs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NameData {
        private Integer id;
        private String name;
    }
}
