package dev.andrescoder.events_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data // Será el dto con el que manejaremos la respuesta que el servidor envía al cliente
public class EventResponseDto {
    private Long id;
    private String name;
    private LocalDate date;
    private String location;
}
