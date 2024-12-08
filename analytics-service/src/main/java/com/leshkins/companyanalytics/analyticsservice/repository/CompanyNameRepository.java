package com.leshkins.companyanalytics.analyticsservice.repository;

import com.leshkins.companyanalytics.analyticsservice.model.CompanyName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyNameRepository extends JpaRepository<CompanyName, Long> {
}
