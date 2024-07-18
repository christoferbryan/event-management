package com.events.eventmanagement.ticket.service.impl;

import com.events.eventmanagement.exceptions.DataNotFoundException;
import com.events.eventmanagement.exceptions.InputException;
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

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Ticket not found"));
    }

    @Override
    public void reduceSeats(Ticket ticket, int seats) {
        if(ticket.getAvailableSeats() == 0){
            throw new InputException("Ticket sold out");
        }

        int availableSeats = ticket.getAvailableSeats() - seats;

        ticket.setAvailableSeats(availableSeats);
    }
}
