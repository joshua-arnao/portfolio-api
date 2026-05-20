package com.autodidacta.portafolioapi.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectSection {
    private String description;
    private Map<String, String> pareto;
    private Map<String, String> list;
    private Map<String, String> images;
}
