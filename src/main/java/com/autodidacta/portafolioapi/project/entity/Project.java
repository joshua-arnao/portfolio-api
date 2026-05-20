package com.autodidacta.portafolioapi.project.entity;

import com.autodidacta.portafolioapi.shared.config.JsonConverter;
import com.autodidacta.portafolioapi.shared.config.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "img_card")
    private String imgCard;

    @Column(nullable = false)
    private Boolean featured;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private List<String> tools;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private List<String> type;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private List<String> link;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private List<String> rol;

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private ProjectSection problem;

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private ProjectSection understanding;

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private ProjectSection breacking;

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private ProjectSection overall;

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private ProjectSection conclusions;

    @Column(name = "sort_order")
    private Integer sortOrder;
}
