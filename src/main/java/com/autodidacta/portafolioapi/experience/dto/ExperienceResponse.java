package com.autodidacta.portafolioapi.experience.dto;

public record ExperienceResponse(
        Long id,
        String type,
        String year,
        String title,
        String link
) {
}
