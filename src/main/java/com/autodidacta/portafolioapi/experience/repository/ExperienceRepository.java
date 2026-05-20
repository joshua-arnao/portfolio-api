package com.autodidacta.portafolioapi.experience.repository;

import com.autodidacta.portafolioapi.experience.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
