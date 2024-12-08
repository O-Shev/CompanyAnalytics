package com.leshkins.companyanalytics.analyticsservice.serde;

import com.leshkins.companyanalytics.analyticsservice.event.AddressCategoryEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class AddressCategorySerde extends Serdes.WrapperSerde<AddressCategoryEvent> {
    public AddressCategorySerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(AddressCategoryEvent.class));
    }
}
