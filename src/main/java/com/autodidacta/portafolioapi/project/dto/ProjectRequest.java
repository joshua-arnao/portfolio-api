package com.autodidacta.portafolioapi.project.dto;

import com.autodidacta.portafolioapi.project.entity.ProjectSection;

import java.util.List;

public record ProjectRequest(
        String title,
        String description,
        String imgCard,
        Boolean featured,
        List<String> tools,
        List<String> type,
        List<String> link,
        List<String> rol,
        ProjectSection problem,
        ProjectSection understanding,
        ProjectSection breacking,
        ProjectSection overall,
        ProjectSection conclusions,
        Integer sortOrder
) {
}
