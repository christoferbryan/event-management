package com.events.eventmanagement.ticket.service;

import com.events.eventmanagement.ticket.entity.Ticket;

public interface TicketService {
    void saveNewTicket(Ticket newTicket);
    Ticket getTicketById(Long id);

    void reduceSeats(Ticket ticket, int availableSeats);
}
