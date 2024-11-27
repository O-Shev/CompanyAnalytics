package com.leshkins.companyanalytics.company_service.repository;

import com.leshkins.companyanalytics.company_service.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {


}
