package com.imt.service.event.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.imt.service.event.constant.EventExemples;
import com.imt.service.event.dto.EventDto;
import com.imt.service.event.entity.EventEntity;
import com.imt.service.event.mapper.EventMapper;
import com.imt.service.event.repository.EventRepository;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;

    @BeforeEach
    private void prepareData () {
        eventRepository.deleteAll();
    }

    @Test
    void shouldBeOrganized () {
        EventDto originalEvent = EventExemples.Event_3.toDto();

        EventDto newEvent = eventService.organizedEvent(originalEvent);
        Assertions.assertEquals(originalEvent.title(), newEvent.title());        
        Assertions.assertEquals(originalEvent.description(), newEvent.description());        
        Assertions.assertEquals(originalEvent.ticketPrice(), newEvent.ticketPrice());        
        Assertions.assertEquals(originalEvent.organizer(), newEvent.organizer());        
        Assertions.assertEquals(originalEvent.datetime(), newEvent.datetime());        
    }


    @Test
    void shouldNotBeOrganizedBecauseLocationIsMissing () {
        EventEntity entity = EventExemples.Event_3.toEntity();;
        entity.setLocation(null);

        Assertions.assertThrows(RuntimeException.class, () -> 
            eventService.organizedEvent(EventMapper.mapToDto(entity)));

    }

}
