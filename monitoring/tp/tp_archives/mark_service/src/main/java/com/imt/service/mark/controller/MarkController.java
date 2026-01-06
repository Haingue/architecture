package com.imt.service.mark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.exception.EventNotFoundException;
import com.imt.service.mark.exception.MarkNotFoundException;
import com.imt.service.mark.exception.ParticipantNotFoundException;
import com.imt.service.mark.service.MarkService;


@RestController
public class MarkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarkController.class);

    @Autowired
    private MarkService markService;
    
    @PostMapping("/evaluate")
    public ResponseEntity<?> evaluateEvent(@RequestBody MarkDto markDto) {
        LOGGER.info("Evaluate event: {}", markDto);
        try {
            return ResponseEntity
            .status(HttpStatus.CREATED.value())
            .body(markService.evaluateEvent(markDto));
        } catch (EventNotFoundException exception) {
            return ResponseEntity.badRequest().body("Event not Found");
        } catch (ParticipantNotFoundException exception) {
            return ResponseEntity.badRequest().body("Participant not Found");
        } catch (Exception exception) {
            LOGGER.error("Error", exception.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/mark")
    public ResponseEntity<?> updateEventEvaluation(@RequestBody MarkDto markDto) {
        LOGGER.info("Update mark: {}", markDto);
        try {
            return ResponseEntity
            .ok(markService.updateMark(markDto));
        } catch (EventNotFoundException exception) {
            return ResponseEntity.badRequest().body("Event not Found");
        } catch (ParticipantNotFoundException exception) {
            return ResponseEntity.badRequest().body("Participant not Found");
        } catch (MarkNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            LOGGER.error("Error", exception.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
