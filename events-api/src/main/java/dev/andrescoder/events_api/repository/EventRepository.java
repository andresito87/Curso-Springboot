package dev.andrescoder.events_api.repository;

import dev.andrescoder.events_api.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring al extender de JpaRepository va a crear un bean de esta clase y la inyectará en los controladores
public interface EventRepository extends JpaRepository<Event, Long> {
}
