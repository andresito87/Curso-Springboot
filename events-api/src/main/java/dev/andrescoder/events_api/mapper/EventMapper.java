package dev.andrescoder.events_api.mapper;

import dev.andrescoder.events_api.domain.Event;
import dev.andrescoder.events_api.dto.EventRequestDto;
import dev.andrescoder.events_api.dto.EventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

// Indica que es un componente de Spring y que debe crear un bean de esta interfaz para poder inyectarla en otros
// componentes. Creará el código en tiempo de compilación para mapear entre Event y EventResponseDto
@Mapper(componentModel = "spring")
public interface EventMapper {

    // MapStruct generará automáticamente la implementación de este methodo para mapear un EventRequestDto a un Event
    Event toEntity(EventRequestDto eventRequestDto);

    // MapStruct generará automáticamente la implementación de este methodo para mapear un Event a un EventResponseDto
    EventResponseDto toResponseDto(Event event);

    // Automáticamente permite mapear una lista de eventos en una lista de EventResponseDto, ya que MapStruct sabe
    // cómo mapear cada elemento de la lista
    List<EventResponseDto> toEventResponseDtoList(List<Event> events);

    // MapStruct generará automáticamente la implementación de este methodo para actualizar un Event existente a
    // partir de un EventRequestDto. El @MappingTarget indica que el objeto event es el objetivo de la actualización.
    // Los datos pasarán del eventRequestDto al event, actualizando sus campos con los valores del DTO.
    void updateEventFromDto(EventRequestDto eventRequestDto, @MappingTarget Event event);

}
