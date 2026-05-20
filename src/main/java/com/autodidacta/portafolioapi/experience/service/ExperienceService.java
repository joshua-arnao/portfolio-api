package com.autodidacta.portafolioapi.experience.service;

import com.autodidacta.portafolioapi.experience.dto.ExperienceRequest;
import com.autodidacta.portafolioapi.experience.dto.ExperienceResponse;
import com.autodidacta.portafolioapi.experience.entity.Experience;
import com.autodidacta.portafolioapi.experience.repository.ExperienceRepository;
import com.autodidacta.portafolioapi.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Transactional(readOnly = true)
    public List<ExperienceResponse> findAll() {
        return experienceRepository.findAll()
                .stream()
                .map(exp -> new ExperienceResponse(
                        exp.getId(),
                        exp.getType(),
                        exp.getYear(),
                        exp.getTitle(),
                        exp.getLink()
                ))
                .toList();
    }

    @Transactional
    public ExperienceResponse create(ExperienceRequest request) {
        Experience exp = Experience.builder()
                .type(request.type())
                .year(request.year())
                .title(request.title())
                .link(request.link())
                .build();
        Experience saved = experienceRepository.save(exp);
        return toResponse(saved);
    }

    @Transactional
    public ExperienceResponse update(Long id, ExperienceRequest request) {
        Experience exp = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experiencia no encontrada con id: " + id));
        exp.setType(request.type());
        exp.setYear(request.year());
        exp.setTitle(request.title());
        exp.setLink(request.link());
        Experience saved = experienceRepository.save(exp);
        return toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Experiencia no encontrada con id: " + id);
        }
        experienceRepository.deleteById(id);
    }

    private ExperienceResponse toResponse(Experience exp) {
        return new ExperienceResponse(
                exp.getId(),
                exp.getType(),
                exp.getYear(),
                exp.getTitle(),
                exp.getLink()
        );
    }
}
