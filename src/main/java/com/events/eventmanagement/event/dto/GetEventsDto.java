package com.events.eventmanagement.event.dto;

import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.ticket.entity.Ticket;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class GetEventsDto {
    private String title;
    private String description;
    private String location;
    private String date;
    private String time;
    private String category;
    private String lowestPrice;

    public static GetEventsDto toDto(Event event){
        GetEventsDto getEventsDto = new GetEventsDto();

        getEventsDto.setTitle(event.getTitle());
        getEventsDto.setDescription(event.getDescription());
        getEventsDto.setLocation(event.getLocation());
        getEventsDto.setDate(event.getDate().toString());
        getEventsDto.setTime(event.getTime().toString());
        getEventsDto.setCategory(event.getCategory().getName());

        List<Integer> ticketPrices = event.getTickets().stream().map(Ticket::getPrice).toList();

        Integer minPrice = Collections.min(ticketPrices);

        if(minPrice > 0){
            getEventsDto.setLowestPrice(minPrice.toString());
        }
        else if(minPrice == 0){
            getEventsDto.setLowestPrice("Free");
        }

        return getEventsDto;
    }
}
