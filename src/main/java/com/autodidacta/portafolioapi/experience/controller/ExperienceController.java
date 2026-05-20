package com.autodidacta.portafolioapi.experience.controller;

import com.autodidacta.portafolioapi.experience.dto.ExperienceResponse;
import com.autodidacta.portafolioapi.experience.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
