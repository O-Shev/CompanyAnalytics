package com.leshkins.companyanalytics.company_service.repository;

import com.leshkins.companyanalytics.company_service.model.entity.Address;
import com.leshkins.companyanalytics.company_service.model.entity.AddressCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressCategoryRepository extends JpaRepository<AddressCategory, Long> {

    List<AddressCategory> findAllByNameIn(List<String> names);
}
