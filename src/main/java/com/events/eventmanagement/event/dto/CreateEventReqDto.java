package com.events.eventmanagement.event.dto;

import com.events.eventmanagement.coupon.dto.CouponDto;
import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.ticket.dto.TicketDto;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateEventReqDto {
    private String title;
    private String description;
    private String location;
    private Boolean isPaid;
    private LocalDate date;
    private LocalTime time;
    private Long categoryId;
    private List<TicketDto> tickets;
    private List<CouponDto> coupons;

    public Event toEntity(){
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setLocation(location);
        event.setIsPaid(isPaid);
        event.setDate(date);
        event.setTime(time);

        return event;
    }
}
