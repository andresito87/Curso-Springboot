package dev.andrescoder.portfoliobackend.repository.interfaces;

import dev.andrescoder.portfoliobackend.model.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    List<Project> findAll();

    Optional<Project> findById(Long id);

    Project save(Project project);

    void delete(Long id);
}
