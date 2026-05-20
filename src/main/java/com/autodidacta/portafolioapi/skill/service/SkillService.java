package com.autodidacta.portafolioapi.skill.service;

import com.autodidacta.portafolioapi.shared.exceptions.ResourceNotFoundException;
import com.autodidacta.portafolioapi.skill.dto.SkillRequest;
import com.autodidacta.portafolioapi.skill.dto.SkillResponse;
import com.autodidacta.portafolioapi.skill.entity.Skill;
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

    @Transactional
    public SkillResponse create(SkillRequest request) {
        Skill skill = Skill.builder()
                .name(request.name())
                .type(request.type())
                .build();
        Skill saved = skillRepository.save(skill);
        return new SkillResponse(saved.getId(), saved.getName(), saved.getType());
    }

    @Transactional
    public SkillResponse update(Long id, SkillRequest request) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Skill no encontrado con id: " + id));
        skill.setName(request.name());
        skill.setType(request.type());
        Skill saved = skillRepository.save(skill);
        return new SkillResponse(saved.getId(), saved.getName(), saved.getType());
    }

    @Transactional
    public void delete(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill no encontrado con id: " + id);
        }
        skillRepository.deleteById(id);
    }
}
