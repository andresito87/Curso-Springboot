package dev.andrescoder.portfoliobackend.model;

import jakarta.validation.constraints.Min;
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
public class Education {
    // No se valida aquí porque no se maneja en el formulario, no la introduce el usuario, es config de la base de datos
    private Long id;
    @NotBlank(message = "Degree can not be empty")
    private String degree;
    @NotBlank(message = "Institution can not be empty")
    private String institution;
    @NotNull(message = "Start date can not be null")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;
    @PastOrPresent(message = "End date cannot be in the future")
    private LocalDate endDate;
    @NotBlank(message = "Description can not be empty")
    private String description;
    // No se valida aquí porque no se maneja en el formulario, no la introduce el usuario, es config de la base de datos
    private Long personalInfoId;
}
