package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceRepository {
    Experience save(Experience experience);

    Optional<Experience> findById(Long id);

    List<Experience> findAll();

    void deleteById(Long id);

    List<Experience> findExperiencesByPersonalInfoId(Long personalInfoId);
}
