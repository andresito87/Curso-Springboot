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
@NoArgsConstructor
@AllArgsConstructor
public class EducationDto {

    private Long id; // No se valida aquí porque no se maneja en el formulario, no la introduce el usuario

    @NotBlank(message = "Degree can not be empty")
    private String degree;
    @NotBlank(message = "Institution can not be empty")
    private String institution;
    @NotNull(message = "Start date can not be null")
    @PastOrPresent(message = "Start date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @PastOrPresent(message = "End date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotBlank(message = "Description can not be empty")
    private String description;
    @NotNull(message = "Personal info ID can not be null")
    private Long personalInfoId;
}