package com.leshkins.companyanalytics.company_service.validator;

import com.leshkins.companyanalytics.company_service.dto.address.CreateAddressDto;
import com.leshkins.companyanalytics.company_service.exception.DataValidationException;
import com.leshkins.companyanalytics.company_service.model.entity.Address;
import com.leshkins.companyanalytics.company_service.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressValidator {
    private final AddressRepository addressRepository;

    public void validateCreateAddress(CreateAddressDto createAddressDto) {
        Optional<Address> addressOptional =  addressRepository.findFirstByCountryAndCityAndStreetAndZip(
                createAddressDto.country(), createAddressDto.city(), createAddressDto.street(), createAddressDto.zip());

        if(addressOptional.isPresent()) throw new DataValidationException("address already exist");
    }
}
