package dev.andrescoder.events_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Date cannot be null")
    private LocalDate date;
    @NotBlank(message = "Location cannot be blank")
    private String location;
}
