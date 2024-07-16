package com.events.eventmanagement.event.service;

import com.events.eventmanagement.event.dto.CreateEventReqDto;
import com.events.eventmanagement.event.dto.CreateEventRespDto;

public interface EventService {
    CreateEventRespDto createEvent(CreateEventReqDto createEventReqDto, String email);
}
