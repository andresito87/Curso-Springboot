package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.Project;
import dev.andrescoder.portfoliobackend.repository.interfaces.IProjectRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    private final IProjectRepository projectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(long id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void delete(long id) {
        projectRepository.delete(id);
    }
}
