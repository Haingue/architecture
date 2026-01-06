package com.imt.service.mark.service;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.imt.service.mark.constant.EventExemples;
import com.imt.service.mark.constant.MarkExemples;
import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.entity.MarkEntity;
import com.imt.service.mark.mapper.MarkMapper;
import com.imt.service.mark.repository.EventRepository;
import com.imt.service.mark.repository.MarkRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private MarkService markService;

    @BeforeEach
    void prepareTest () {
        markRepository.deleteAll();
        eventRepository.deleteAll();
        eventRepository.save(EventExemples.Event_3.toEntity());
        for (int i = 0; i < 5; i++) {
            MarkEntity entity = MarkExemples.Mark_1.toEntity();
            entity.setEvent(EventExemples.Event_3.toEntity());
            entity.setParticipant(UUID.randomUUID());
            entity.setMarkValue(5);
            markService.evaluateEvent(MarkMapper.mapToDto(entity));
        }
        eventRepository.save(EventExemples.Event_2.toEntity());
        for (int i = 0; i < 2; i++) {
            MarkEntity entity = MarkExemples.Mark_2.toEntity();
            entity.setEvent(EventExemples.Event_2.toEntity());
            entity.setParticipant(UUID.randomUUID());
            entity.setMarkValue(3);
            markService.evaluateEvent(MarkMapper.mapToDto(entity));
        }
        eventRepository.save(EventExemples.Event_1.toEntity());
        for (int i = 0; i > 1; i++) {
            MarkEntity entity = MarkExemples.Mark_2.toEntity();
            entity.setEvent(EventExemples.Event_2.toEntity());
            entity.setParticipant(UUID.randomUUID());
            entity.setMarkValue(3);
            markService.evaluateEvent(MarkMapper.mapToDto(entity));
        }
    }

    @Test
    @Transactional
    void shouldReturnExemple3First () {
        EventDto topEventByMarks = eventService.findTopEventByMarks();
        Assertions.assertEquals(EventExemples.Event_3.id, topEventByMarks.id());
        Assertions.assertEquals(EventExemples.Event_3.title, topEventByMarks.title());
    }
}
