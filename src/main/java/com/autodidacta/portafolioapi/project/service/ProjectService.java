package com.autodidacta.portafolioapi.project.service;

import com.autodidacta.portafolioapi.project.dto.ProjectResponse;
import com.autodidacta.portafolioapi.project.entity.Project;
import com.autodidacta.portafolioapi.project.repository.ProjectRepository;
import com.autodidacta.portafolioapi.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<ProjectResponse> findAll() {
        return projectRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProjectResponse findById(Long id) {
        return projectRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proyecto no encontrado con id: " + id
                ));
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getImgCard(),
                project.getFeatured(),
                project.getTools(),
                project.getType(),
                project.getLink(),
                project.getRol(),
                project.getProblem(),
                project.getUnderstanding(),
                project.getBreacking(),
                project.getOverall(),
                project.getConclusions()
        );
    }
}
