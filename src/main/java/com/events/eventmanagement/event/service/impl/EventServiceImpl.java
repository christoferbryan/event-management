package com.events.eventmanagement.event.service.impl;

import com.events.eventmanagement.category.service.CategoryService;
import com.events.eventmanagement.coupon.dto.CouponDto;
import com.events.eventmanagement.coupon.entity.Coupon;
import com.events.eventmanagement.coupon.service.CouponService;
import com.events.eventmanagement.event.dto.CreateEventReqDto;
import com.events.eventmanagement.event.dto.CreateEventRespDto;
import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.event.repository.EventRepository;
import com.events.eventmanagement.event.service.EventService;
import com.events.eventmanagement.ticket.dto.TicketDto;
import com.events.eventmanagement.ticket.entity.Ticket;
import com.events.eventmanagement.ticket.service.TicketService;
import com.events.eventmanagement.users.entity.User;
import com.events.eventmanagement.users.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final UserService userService;
    private final EventRepository eventRepository;
    private final CategoryService categoryService;
    private final TicketService ticketService;
    private final CouponService couponService;

    public EventServiceImpl(UserService userService, EventRepository eventRepository, CategoryService categoryService, TicketService ticketService, CouponService couponService){
        this.userService = userService;
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.ticketService = ticketService;
        this.couponService = couponService;
    }
    @Override
    public CreateEventRespDto createEvent(CreateEventReqDto createEventReqDto, String email) {
        Event newEvent = createEventReqDto.toEntity();
        User organizer = userService.getUserByEmail(email);
        newEvent.setOrganizer(organizer);
        newEvent.setCategory(categoryService.getCategoryById(createEventReqDto.getCategoryId()));
        eventRepository.save(newEvent);

        for(TicketDto ticketData : createEventReqDto.getTickets()){
            Ticket ticket = ticketData.toEntity();
            ticket.setEvent(newEvent);
            ticketService.saveNewTicket(ticket);
        }

        for(CouponDto couponData : createEventReqDto.getCoupons()){
            couponService.createCoupon(couponData, newEvent);
        }

        return CreateEventRespDto.toDto(newEvent);
    }
}
