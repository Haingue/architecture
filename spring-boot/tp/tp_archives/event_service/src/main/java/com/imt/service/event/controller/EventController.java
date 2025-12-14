package com.imt.service.event.controller;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imt.service.event.dto.EventDto;
import com.imt.service.event.service.EventService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/event")
public class EventController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<?> getEvent (@PathParam(value = "id") Optional<UUID> id) {
        LOGGER.info("Load one event: {}", id);
        try {
            if (id.isPresent()) {
                return ResponseEntity.ok(eventService.findEvent(id.get()));
            }
            return ResponseEntity.ok(eventService.findAllEvent());
        } catch (RuntimeException exception) {
            LOGGER.warn("Event not found", exception);
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            LOGGER.error("Error", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> postOrganizeEvent(@RequestBody EventDto eventDto) {
        LOGGER.info("Organize new event: {}", eventDto);
        try {
            return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(eventService.organizedEvent(eventDto));
        } catch (RuntimeException exception) {
            LOGGER.warn("Bad request", exception);
            return ResponseEntity.badRequest().build();
        } catch (Exception exception) {
            LOGGER.error("Error", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> putUpdateEvent(@RequestBody EventDto eventDto) {
        LOGGER.info("Update event: {}", eventDto);
        try {
            return ResponseEntity
                .ok(eventService.updateEvent(eventDto));
        } catch (RuntimeException exception) {
            LOGGER.warn("Bad request", exception);
            return ResponseEntity.badRequest().build();
        } catch (Exception exception) {
            LOGGER.error("Error", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

}
