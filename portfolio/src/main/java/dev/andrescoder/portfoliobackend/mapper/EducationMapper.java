package dev.andrescoder.portfoliobackend.mapper;

import dev.andrescoder.portfoliobackend.dto.EducationDto;
import dev.andrescoder.portfoliobackend.model.Education;

public class EducationMapper {

    public static EducationDto toDto(Education education) {
        if (education == null) {
            return null;
        }
        return new EducationDto(
                education.getId(),
                education.getDegree(),
                education.getInstitution(),
                education.getStartDate(),
                education.getEndDate(),
                education.getDescription(),
                education.getPersonalInfoId()
        );
    }

    public static Education toEntity(EducationDto educationDto) {
        if (educationDto == null) {
            return null;
        }
        return new Education(
                educationDto.getId(),
                educationDto.getDegree(),
                educationDto.getInstitution(),
                educationDto.getStartDate(),
                educationDto.getEndDate(),
                educationDto.getDescription(),
                educationDto.getPersonalInfoId()
        );
    }

}
