package com.events.eventmanagement.event.dto;

import com.events.eventmanagement.event.entity.Event;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateEventRespDto {
    private String title;
    private String description;
    private String category;
    private String location;
    private Boolean isPaid;
    private LocalDate date;
    private LocalTime time;

    public static CreateEventRespDto toDto(Event event){
        CreateEventRespDto createEventRespDto = new CreateEventRespDto();

        createEventRespDto.setTitle(event.getTitle());
        createEventRespDto.setDescription(event.getDescription());
        createEventRespDto.setCategory(event.getCategory().getName());
        createEventRespDto.setLocation(event.getLocation());
        createEventRespDto.setIsPaid(event.getIsPaid());
        createEventRespDto.setDate(event.getDate());
        createEventRespDto.setTime(event.getTime());

        return createEventRespDto;
    }
}
