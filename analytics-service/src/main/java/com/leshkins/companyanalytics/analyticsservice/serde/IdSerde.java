package com.leshkins.companyanalytics.analyticsservice.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class IdSerde extends Serdes.WrapperSerde<Integer> {
    public IdSerde() {
        super(new IdSerializer(), new IdDeserializer());
    }

    // Custom Serializer for Integer
    public static class IdSerializer implements Serializer<Integer> {
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public byte[] serialize(String topic, Integer data) {
            try {
                if (data == null) {
                    return null;
                }
                // Serialize Integer as {"id": <value>}
                return objectMapper.writeValueAsBytes(Map.of("id", data));
            } catch (Exception e) {
                throw new RuntimeException("Error serializing Integer: " + e.getMessage(), e);
            }
        }

        @Override
        public void close() {
            // No resources to close
        }
    }

    // Custom Deserializer for Integer
    public static class IdDeserializer implements Deserializer<Integer> {
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public Integer deserialize(String topic, byte[] data) {
            try {
                if (data == null || data.length == 0) {
                    return null;
                }
                // Deserialize JSON {"id": <value>} to Integer
                Map<String, Integer> map = objectMapper.readValue(data, Map.class);
                return map.get("id");
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing Integer: " + e.getMessage(), e);
            }
        }

        @Override
        public void close() {
            // No resources to close
        }
    }
}
