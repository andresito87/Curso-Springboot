package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceService {
    Experience save(Experience experience) throws IllegalArgumentException;

    Optional<Experience> findById(Long id);

    List<Experience> findAll();

    void deleteById(Long id);

    List<Experience> findExperiencesByPersonalInfoId(Long personalInfoId);
}
