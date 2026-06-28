package dev.andrescoder.portfoliobackend.mapper;

import dev.andrescoder.portfoliobackend.dto.PersonalInfoDto;
import dev.andrescoder.portfoliobackend.model.PersonalInfo;

public class PersonalInfoMapper {

    public static PersonalInfoDto toDto(PersonalInfo personalInfo) {
        if (personalInfo == null) {
            return null;
        }
        return new PersonalInfoDto(
                personalInfo.getId(),
                personalInfo.getFirstName(),
                personalInfo.getLastName(),
                personalInfo.getTitle(),
                personalInfo.getEmail(),
                personalInfo.getPhone(),
                personalInfo.getProfileDescription(),
                personalInfo.getProfileImageUrl(),
                personalInfo.getYearsOfExperience(),
                personalInfo.getLinkedinUrl(),
                personalInfo.getGithubUrl()
        );
    }

    public static PersonalInfo toEntity(PersonalInfoDto personalInfoDto) {
        if (personalInfoDto == null) {
            return null;
        }
        return new PersonalInfo(
                personalInfoDto.getId(),
                personalInfoDto.getFirstName(),
                personalInfoDto.getLastName(),
                personalInfoDto.getTitle(),
                personalInfoDto.getEmail(),
                personalInfoDto.getPhone(),
                personalInfoDto.getProfileDescription(),
                personalInfoDto.getProfileImageUrl(),
                personalInfoDto.getYearsOfExperience(),
                personalInfoDto.getLinkedinUrl(),
                personalInfoDto.getGithubUrl()
        );
    }

}
