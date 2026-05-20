package com.autodidacta.portafolioapi.project.controller;

import com.autodidacta.portafolioapi.project.dto.ProjectResponse;
import com.autodidacta.portafolioapi.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projects", description = "Endpoints para proyectos del portfolio")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @Operation(summary = "Obtener todos los proyectos ordenados")
    public ResponseEntity<List<ProjectResponse>> findAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proyecto por ID")
    public ResponseEntity<ProjectResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }
}
