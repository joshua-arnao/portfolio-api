package com.autodidacta.portafolioapi.shared.config;

import com.autodidacta.portafolioapi.project.entity.ProjectSection;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonConverter implements AttributeConverter<ProjectSection, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ProjectSection attribute) {
        if (attribute == null) return null;
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error serializando ProjectSection a JSON", e);
        }
    }

    @Override
    public ProjectSection convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return objectMapper.readValue(dbData, ProjectSection.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializando JSON a ProjectSection", e);
        }
    }
}
