package com.imt.service.mark.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.imt.service.mark.dto.EventDto;

@Service
public class EventService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    public EventDto findTopEventByMarks () {
        throw new RuntimeException();
    }
}
