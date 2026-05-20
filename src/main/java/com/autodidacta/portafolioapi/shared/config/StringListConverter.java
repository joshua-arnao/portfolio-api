package com.autodidacta.portafolioapi.shared.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = false)
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null) return null;
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error serializando List<String> a JSON", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error deserializando JSON a List<String>", e);
        }
    }
}
