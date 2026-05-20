package com.autodidacta.portafolioapi.experience.dto;

public record ExperienceRequest(
        String type,
        String year,
        String title,
        String link
) {
}
