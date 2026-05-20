package com.autodidacta.portafolioapi.skill.controller;

import com.autodidacta.portafolioapi.skill.dto.SkillRequest;
import com.autodidacta.portafolioapi.skill.dto.SkillResponse;
import com.autodidacta.portafolioapi.skill.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @Operation(summary = "Crear un skill")
    public ResponseEntity<SkillResponse> create(@RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un skill")
    public ResponseEntity<SkillResponse> update(
            @PathVariable Long id,
            @RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un skill")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}