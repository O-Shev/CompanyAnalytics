package com.leshkins.companyanalytics.analyticsservice.serde;

import com.leshkins.companyanalytics.analyticsservice.event.AddressEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class AddressSerde extends Serdes.WrapperSerde<AddressEvent>{
    public AddressSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(AddressEvent.class));
    }
}
