package dev.andrescoder.portfoliobackend.mapper;

import dev.andrescoder.portfoliobackend.dto.ProjectDto;
import dev.andrescoder.portfoliobackend.model.Project;

public class ProjectMapper {

    public static ProjectDto toDto(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getImageUrl(),
                project.getProjectUrl(),
                project.getPersonalInfoId()
        );
    }

    public static Project toEntity(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }
        return new Project(
                projectDto.getId(),
                projectDto.getTitle(),
                projectDto.getDescription(),
                projectDto.getImageUrl(),
                projectDto.getProjectUrl(),
                projectDto.getPersonalInfoId()
        );
    }

}
