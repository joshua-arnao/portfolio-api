package com.autodidacta.portafolioapi.project.repository;

import com.autodidacta.portafolioapi.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderBySortOrderAsc();
}