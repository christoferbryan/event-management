package com.events.eventmanagement.event.service;

import com.events.eventmanagement.event.dto.CreateEventReqDto;
import com.events.eventmanagement.event.dto.CreateEventRespDto;
import com.events.eventmanagement.event.dto.GetEventsDto;
import com.events.eventmanagement.event.entity.Event;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    CreateEventRespDto createEvent(CreateEventReqDto createEventReqDto, String email);
    List<GetEventsDto> getAllEvents(int page, int size);
    Event getEventById(Long id);
    GetEventsDto searchEventById(Long id);

    List<GetEventsDto> searchEvents(Pageable pageable, String title, String category, Long userId, LocalDate date, String location);

    void deleteEventById(Long id);
}
