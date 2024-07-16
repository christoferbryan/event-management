package com.events.eventmanagement.event.service;

import com.events.eventmanagement.event.dto.CreateEventReqDto;
import com.events.eventmanagement.event.dto.CreateEventRespDto;
import com.events.eventmanagement.event.dto.GetEventsDto;

import java.util.List;

public interface EventService {
    CreateEventRespDto createEvent(CreateEventReqDto createEventReqDto, String email);
    List<GetEventsDto> getAllEvents(int page, int size);
}
