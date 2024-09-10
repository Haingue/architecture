package com.imt.service.mark.service;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.imt.service.mark.constant.EventExemples;
import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.repository.EventRepository;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;

    @BeforeEach
    void prepareTest () {
        eventRepository.deleteAll();
    }
}
