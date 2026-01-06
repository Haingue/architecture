package com.imt.service.mark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.imt.service.mark.exception.NoEventException;
import com.imt.service.mark.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EventController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("/top-event")
    public ResponseEntity<?> getTopEvent() {
        LOGGER.info("Load top event");
        try {
            return ResponseEntity.ok(eventService.findTopEventByMarks());
        } catch (NoEventException exception) {
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            LOGGER.error("Error", exception.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
