package com.leshkins.companyanalytics.analyticsservice.serde;

import com.leshkins.companyanalytics.analyticsservice.event.CompanyEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class CompanySerde extends Serdes.WrapperSerde<CompanyEvent> {
    public CompanySerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(CompanyEvent.class));
    }
}
