package com.leshkins.companyanalytics.analyticsservice.repository;

import com.leshkins.companyanalytics.analyticsservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
