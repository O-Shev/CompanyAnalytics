package com.leshkins.companyanalytics.analyticsservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leshkins.companyanalytics.analyticsservice.event.CompanyEvent;
import com.leshkins.companyanalytics.analyticsservice.model.Company;
import com.leshkins.companyanalytics.analyticsservice.model.CompanyName;
import com.leshkins.companyanalytics.analyticsservice.repository.CompanyNameRepository;
import com.leshkins.companyanalytics.analyticsservice.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyUpdateListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CompanyRepository companyRepository;
    private final CompanyNameRepository companyNameRepository;

    @Transactional
    //@KafkaListener(topics = "company_server.public.company", groupId = "company_consumer")
    public void listen(String message) {
        try {
            CompanyEvent event = objectMapper.readValue(message, CompanyEvent.class);
            System.out.println("Parsed message: " + event);
            processEvent(event);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse message: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void processEvent(CompanyEvent event) {
        Optional<Company> opCompany = companyRepository.findById(event.getAfter().getId());
        Company company = opCompany.orElse(new Company());
        company.setStatus(event.getAfter().getStatus());


        String newName = event.getAfter().getName();
        String oldName = company.getName();
        List<CompanyName> list = company.getOldNames();
        if(list == null) list = new ArrayList<>();
        if(!newName.equals(oldName)) {
            boolean exists = list.stream().anyMatch(companyName -> companyName.getName().equalsIgnoreCase(oldName));
            if (!exists) {
                list.add(new CompanyName(null, oldName, company));
            }
        }
        company.setName(newName);

        companyRepository.save(company);
        companyNameRepository.saveAll(list);
    }
}
