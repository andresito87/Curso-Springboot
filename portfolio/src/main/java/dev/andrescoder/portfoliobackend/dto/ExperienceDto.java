package dev.andrescoder.portfoliobackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDto {

    private Long id; // No lo validamos porque esto no lo introduce el usuario

    @NotBlank(message = "Job title can not be empty")
    private String jobTitle;
    @NotBlank(message = "Company name can not be empty")
    private String companyName;
    @NotNull(message = "Start date can not be null")
    @PastOrPresent(message = "Start date must not be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @PastOrPresent(message = "End date must not be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotBlank(message = "Description can not be empty")
    private String description;
    @NotNull(message = "Personal info ID can not be null")
    private Long personalInfoId;

}
