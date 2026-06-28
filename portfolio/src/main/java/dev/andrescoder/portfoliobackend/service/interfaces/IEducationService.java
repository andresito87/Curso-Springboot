package dev.andrescoder.portfoliobackend.service.interfaces;

import dev.andrescoder.portfoliobackend.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationService {
    Education save(Education education) throws IllegalArgumentException;

    Optional<Education> findById(Long id);

    List<Education> findAll();

    void deleteById(Long id);

    List<Education> findEducationsByPersonalInfoId(Long personalInfoId);
}
