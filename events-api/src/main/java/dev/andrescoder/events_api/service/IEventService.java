package dev.andrescoder.events_api.service;

import dev.andrescoder.events_api.domain.Event;

import java.util.List;

public interface IEventService {
    List<Event> findAll();
    Event save(Event event); // Utilizado en la creación y la actualización
    Event findById(Long id);
    void deleteById(Long id);
}
