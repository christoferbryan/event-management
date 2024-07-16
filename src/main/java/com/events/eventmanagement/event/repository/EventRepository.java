package com.events.eventmanagement.event.repository;

import com.events.eventmanagement.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
