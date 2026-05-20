package com.autodidacta.portafolioapi.experience.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experiences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String title;

    private String link;
}
