package com.leshkins.companyanalytics.company_service.repository;

import com.leshkins.companyanalytics.company_service.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findFirstByCountryAndCityAndStreetAndZip(String country, String city, String street, String zip);
}
