package com.autodidacta.portafolioapi.skill.service;

import com.autodidacta.portafolioapi.skill.dto.SkillResponse;
import com.autodidacta.portafolioapi.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    @Transactional(readOnly = true)
    public List<SkillResponse> findAll() {
        return skillRepository.findAll()
                .stream()
                .map(skill -> new SkillResponse(
                        skill.getId(),
                        skill.getName(),
                        skill.getType()
                ))
                .toList();
    }
}
