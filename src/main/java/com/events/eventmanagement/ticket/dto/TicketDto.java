package com.events.eventmanagement.ticket.dto;

import com.events.eventmanagement.ticket.entity.Ticket;
import lombok.Data;

@Data
public class TicketDto {
    private String name;
    private Double price;
    private int availableSeats;

    public Ticket toEntity(){
        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setPrice(price);
        ticket.setAvailableSeats(availableSeats);

        return ticket;
    }
}
