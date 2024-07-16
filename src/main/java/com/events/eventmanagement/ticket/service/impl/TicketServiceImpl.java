package com.events.eventmanagement.ticket.service.impl;

import com.events.eventmanagement.ticket.entity.Ticket;
import com.events.eventmanagement.ticket.repository.TicketRepository;
import com.events.eventmanagement.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }
    @Override
    public void saveNewTicket(Ticket newTicket) {
        ticketRepository.save(newTicket);
    }
}
