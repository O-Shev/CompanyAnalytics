package com.leshkins.companyanalytics.analyticsservice.listener;

import com.leshkins.companyanalytics.analyticsservice.event.AddressCategoryEvent;
import com.leshkins.companyanalytics.analyticsservice.event.CompanyEvent;
import com.leshkins.companyanalytics.analyticsservice.serde.SerdeFactory;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyProcessor {

    @Autowired
    public void process(StreamsBuilder builder) {

        KStream<Integer, CompanyEvent> stream =
                builder.stream("company_server.public.company",
                        Consumed.with(SerdeFactory.idSerde(), SerdeFactory.companySerde()));

        KStream<Integer, AddressCategoryEvent> stream_ac =
                builder.stream("company_server.public.address_category",
                        Consumed.with(SerdeFactory.idSerde(), SerdeFactory.addressCategorySerde()));

        // Process and print each record
        stream.foreach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });

        stream_ac.foreach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });

    }
}
