package com.autodidacta.portafolioapi.experience.controller;

import com.autodidacta.portafolioapi.experience.dto.ExperienceRequest;
import com.autodidacta.portafolioapi.experience.dto.ExperienceResponse;
import com.autodidacta.portafolioapi.experience.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
@Tag(name = "Experiences", description = "Endpoints para experiencias del portfolio")
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    @Operation(summary = "Obtener todas las experiencias")
    public ResponseEntity<List<ExperienceResponse>> findAll() {
        return ResponseEntity.ok(experienceService.findAll());
    }

    @PostMapping
    @Operation(summary = "Crear una experiencia")
    public ResponseEntity<ExperienceResponse> create(@RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(experienceService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una experiencia")
    public ResponseEntity<ExperienceResponse> update(
            @PathVariable Long id,
            @RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(experienceService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una experiencia")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
