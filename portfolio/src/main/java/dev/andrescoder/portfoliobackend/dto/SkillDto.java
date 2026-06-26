package dev.andrescoder.portfoliobackend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {

    private Long id; // no lo validamos porque esto no lo introduce el usuario

    @NotBlank(message = "Skill name can not be empty")
    private String name;
    @NotNull(message = "Percentage level can not be null")
    @Min(value = 0, message = "Percentage level must be a positive value")
    @Max(value = 100, message = "Percentage level must be less than or equal to 100")
    private Integer levelPercentage;
    @NotBlank(message = "Icon class can not be empty")
    private String iconClass;
    @NotNull(message = "Personal info ID can not be null")
    private Long personalInfoId;

}
