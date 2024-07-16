package com.events.eventmanagement.ticket.repository;

import com.events.eventmanagement.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
