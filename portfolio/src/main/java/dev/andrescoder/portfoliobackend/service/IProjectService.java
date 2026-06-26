package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {

    List<Project> findAll();

    Optional<Project> findById(long id);

    Project save(Project project);

    void delete(long id);

}
