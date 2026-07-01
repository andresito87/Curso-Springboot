package dev.andrescoder.events_api.controller;

import dev.andrescoder.events_api.domain.Event;
import dev.andrescoder.events_api.dto.EventRequestDto;
import dev.andrescoder.events_api.dto.EventResponseDto;
import dev.andrescoder.events_api.mapper.EventMapper;
import dev.andrescoder.events_api.service.IEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final IEventService eventService;

    @GetMapping
    public List<EventResponseDto> getAllEvents() {
        return eventMapper.toEventResponseDtoList(eventService.findAll());
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@Valid @RequestBody EventRequestDto requestDto) {
        Event eventToSave = eventMapper.toEntity(requestDto);
        Event eventSaved = eventService.save(eventToSave);
        EventResponseDto responseDto = eventMapper.toResponseDto(eventSaved);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        EventResponseDto responseDto = eventMapper.toResponseDto(event);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id,
                                                        @Valid @RequestBody EventRequestDto requestDto) {
        Event eventToUpdate = eventService.findById(id);
        eventMapper.updateEventFromDto(requestDto, eventToUpdate);
        Event eventUpdated = eventService.save(eventToUpdate);
        EventResponseDto responseDto = eventMapper.toResponseDto(eventUpdated);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        // Rest aconseja devolver un status 204 no content cuando se elimina correctamente un recurso
        return ResponseEntity.noContent().build();
    }

}


