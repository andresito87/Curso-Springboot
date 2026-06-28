package dev.andrescoder.portfoliobackend.repository.interfaces;

import dev.andrescoder.portfoliobackend.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationRepository {
    Education save(Education education);

    Optional<Education> findById(Long id);

    List<Education> findAll();

    void deleteById(Long id);

    List<Education> findEducationsByPersonalInfoId(Long personalInfoId);
}
