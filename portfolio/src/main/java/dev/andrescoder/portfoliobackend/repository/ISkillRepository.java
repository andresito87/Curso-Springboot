package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillRepository {
    Skill save(Skill skill);

    Optional<Skill> findById(Long id);

    List<Skill> findAll();

    void deleteById(Long id);

    List<Skill> findSkillsByPersonalInfoId(Long personalInfoId);
}
