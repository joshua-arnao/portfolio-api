package com.autodidacta.portafolioapi.project.service;

import com.autodidacta.portafolioapi.project.dto.ProjectRequest;
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
                        "Proyecto no encontrado con id: " + id));
    }

    @Transactional
    public ProjectResponse create(ProjectRequest request) {
        Project project = buildFromRequest(new Project(), request);
        return toResponse(projectRepository.save(project));
    }

    @Transactional
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proyecto no encontrado con id: " + id));
        buildFromRequest(project, request);
        return toResponse(projectRepository.save(project));
    }

    @Transactional
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Proyecto no encontrado con id: " + id);
        }
        projectRepository.deleteById(id);
    }

    private Project buildFromRequest(Project project, ProjectRequest request) {
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setImgCard(request.imgCard());
        project.setFeatured(request.featured() != null ? request.featured() : false);
        project.setTools(request.tools());
        project.setType(request.type());
        project.setLink(request.link());
        project.setRol(request.rol());
        project.setProblem(request.problem());
        project.setUnderstanding(request.understanding());
        project.setBreacking(request.breacking());
        project.setOverall(request.overall());
        project.setConclusions(request.conclusions());
        project.setSortOrder(request.sortOrder() != null ? request.sortOrder() : 99);
        return project;
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
