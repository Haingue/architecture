package com.imt.service.mark.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.entity.EventEntity;

@Service
public class EventService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    public EventDto createEvent (EventDto eventDto) {
        EventEntity eventEntity = new EventEntity();
        throw new RuntimeException();
    }
}
