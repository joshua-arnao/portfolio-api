package com.autodidacta.portafolioapi.experience.service;

import com.autodidacta.portafolioapi.experience.dto.ExperienceResponse;
import com.autodidacta.portafolioapi.experience.repository.ExperienceRepository;
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
}
