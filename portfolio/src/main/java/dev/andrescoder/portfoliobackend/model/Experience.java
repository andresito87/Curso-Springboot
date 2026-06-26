package dev.andrescoder.portfoliobackend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private Long id; // No lo validamos porque esto no lo introduce el usuario

    @NotBlank(message = "Job title can not be empty")
    private String jobTitle;
    @NotBlank(message = "Company name can not be empty")
    private String companyName;
    @NotNull(message = "Start date can not be null")
    @PastOrPresent(message = "Start date must not be in the future")
    private LocalDate startDate;
    @PastOrPresent(message = "End date must not be in the future")
    private LocalDate endDate;
    @NotBlank(message = "Description can not be empty")
    private String description;

    private Long personalInfoId; // No lo validamos porque esto no lo introduce el usuario
}
