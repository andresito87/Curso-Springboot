package dev.andrescoder.portfoliobackend.mapper;

import dev.andrescoder.portfoliobackend.dto.ExperienceDto;
import dev.andrescoder.portfoliobackend.model.Experience;

public class ExperienceMapper {

    public static ExperienceDto toDto(Experience experience) {
        if (experience == null) {
            return null;
        }
        return new ExperienceDto(
                experience.getId(),
                experience.getJobTitle(),
                experience.getCompanyName(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getDescription(),
                experience.getPersonalInfoId()
        );
    }

    public static Experience toEntity(ExperienceDto experienceDto) {
        if (experienceDto == null) {
            return null;
        }
        return new Experience(
                experienceDto.getId(),
                experienceDto.getJobTitle(),
                experienceDto.getCompanyName(),
                experienceDto.getStartDate(),
                experienceDto.getEndDate(),
                experienceDto.getDescription(),
                experienceDto.getPersonalInfoId()
        );
    }

}
