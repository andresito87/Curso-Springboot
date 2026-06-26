package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillService {
    Skill save(Skill skill) throws IllegalArgumentException;

    Optional<Skill> findById(Long id);

    List<Skill> findAll();

    void deleteById(Long id);

    List<Skill> findSkillsByPersonalInfoId(Long personalInfoId);
}
