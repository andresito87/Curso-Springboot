package dev.andrescoder.portfoliobackend.mapper;

import dev.andrescoder.portfoliobackend.dto.SkillDto;
import dev.andrescoder.portfoliobackend.model.Skill;

public class SkillMapper {

    public static SkillDto toDto(Skill skill) {
        if (skill == null) {
            return null;
        }
        return new SkillDto(
                skill.getId(),
                skill.getName(),
                skill.getLevelPercentage(),
                skill.getIconClass(),
                skill.getPersonalInfoId()
        );
    }

    public static Skill toEntity(SkillDto skillDto) {
        if (skillDto == null) {
            return null;
        }
        return new Skill(
                skillDto.getId(),
                skillDto.getName(),
                skillDto.getLevelPercentage(),
                skillDto.getIconClass(),
                skillDto.getPersonalInfoId()
        );
    }

}
