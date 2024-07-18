package com.events.eventmanagement.event.controller;


import com.events.eventmanagement.auth.helper.Claims;
import com.events.eventmanagement.event.dto.CreateEventReqDto;
import com.events.eventmanagement.event.dto.CreateEventRespDto;
import com.events.eventmanagement.event.entity.Event;
import com.events.eventmanagement.event.service.EventService;
import com.events.eventmanagement.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("/create-event")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventReqDto createEventReqDto){
        var claims = Claims.getClaimsFromJwt();
        String email = (String) claims.get("sub");

        return Response.successResponse("Event created successfully", eventService.createEvent(createEventReqDto, email));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "6") int size ){
        return Response.successResponse("Events are retrieved successfully", eventService.getAllEvents(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchEventById(@PathVariable("id") Long id){
        return Response.successResponse("Event with id " + id + " fetched successfully", eventService.searchEventById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable("id") Long id){
        eventService.deleteEventById(id);

        return Response.successResponse("Delete event successful");
    }
}
