package dev.andrescoder.events_api.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity // Springboot indica que esta clase será una tabla en la BD
@Table(name = "events") // Le indica el nombre de la tabla en la BD
public class Event {

    @Id // Es un atributo único en la tabla de la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenerado
    private Long id;

    private String name;
    private LocalDate date;
    private String location;

}
