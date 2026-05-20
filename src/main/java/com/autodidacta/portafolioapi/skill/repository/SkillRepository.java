package com.autodidacta.portafolioapi.skill.repository;

import com.autodidacta.portafolioapi.skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
