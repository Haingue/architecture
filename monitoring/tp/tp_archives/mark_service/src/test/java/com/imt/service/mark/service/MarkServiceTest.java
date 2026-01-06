package com.imt.service.mark.service;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.imt.service.mark.constant.EventExemples;
import com.imt.service.mark.constant.MarkExemples;
import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.entity.EventEntity;
import com.imt.service.mark.entity.MarkEntity;
import com.imt.service.mark.exception.EventNotFoundException;
import com.imt.service.mark.exception.MarkNotFoundException;
import com.imt.service.mark.mapper.MarkMapper;
import com.imt.service.mark.repository.EventRepository;
import com.imt.service.mark.repository.MarkRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MarkServiceTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private MarkService markService;

    @BeforeEach
    @Transactional
    void prepareTest () {
        eventRepository.deleteAll();
        EventEntity eventEntity = EventExemples.Event_1.toEntity();
        eventEntity = eventRepository.save(eventEntity);
        markRepository.deleteAll();
    }

    @Test
    public void shouldBeOrganized () {
        MarkDto originalDto = MarkExemples.Mark_1.toDto();
        MarkDto newDto = markService.evaluateEvent(originalDto);

        Assertions.assertNotNull(newDto);
        Assertions.assertNotNull(newDto.id());
        Assertions.assertNotNull(newDto.creationDatetime());

        Assertions.assertEquals(originalDto.markValue(), newDto.markValue());
        Assertions.assertEquals(originalDto.participant(), newDto.participant());
        Assertions.assertEquals(originalDto.event(), newDto.event());
    }

    @Test
    public void shouldNotBeOrganized () {
        MarkEntity originalEntity = MarkExemples.Mark_1.toEntity();
        originalEntity.setEvent(null);

        Assertions.assertThrows(EventNotFoundException.class, ()->{
            markService.evaluateEvent(MarkMapper.mapToDto(originalEntity));
        });
    }

    @Test
    @Transactional
    public void shouldBeUpdated () {
        // prepare data
        MarkEntity originalEntity = MarkExemples.Mark_1.toEntity();
        originalEntity.setEvent(EventExemples.Event_1.toEntity());
        originalEntity = markRepository.save(originalEntity);

        // run business method
        originalEntity.setMarkValue(1);
        MarkDto updatedMark = markService.updateMark(MarkMapper.mapToDto(originalEntity));

        // compare the result
        Assertions.assertEquals(1, updatedMark.markValue());
    }

    @Test
    public void shouldNotBeUpdatedBecauseNotFound () {
        // prepare data
        MarkEntity originalEntity = MarkExemples.Mark_1.toEntity();
        originalEntity.setId(UUID.randomUUID());

        // run business method
        Assertions.assertThrows(MarkNotFoundException.class, () -> {
            markService.updateMark(MarkMapper.mapToDto(originalEntity));
        });
    }
}
