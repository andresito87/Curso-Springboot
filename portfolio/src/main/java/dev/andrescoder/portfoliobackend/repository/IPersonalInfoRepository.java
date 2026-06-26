package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.PersonalInfo;

import java.util.List;
import java.util.Optional;

public interface IPersonalInfoRepository {
    PersonalInfo save(PersonalInfo personalInfo);

    Optional<PersonalInfo> findById(Long id);

    List<PersonalInfo> findAll();

    void deleteById(Long id);
}
