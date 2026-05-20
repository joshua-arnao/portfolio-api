package com.autodidacta.portafolioapi.skill.controller;

import com.autodidacta.portafolioapi.skill.dto.SkillResponse;
import com.autodidacta.portafolioapi.skill.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@Tag(name = "Skills", description = "Endpoints para skills del portfolio")
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    @Operation(summary = "Obtener todos los skills")
    public ResponseEntity<List<SkillResponse>> findAll() {
        return ResponseEntity.ok(skillService.findAll());
    }
}
