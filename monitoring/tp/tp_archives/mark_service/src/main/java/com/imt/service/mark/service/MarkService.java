package com.imt.service.mark.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.entity.MarkEntity;
import com.imt.service.mark.exception.EventNotFoundException;
import com.imt.service.mark.exception.MarkNotFoundException;
import com.imt.service.mark.exception.ParticipantNotFoundException;
import com.imt.service.mark.mapper.EventMapper;
import com.imt.service.mark.mapper.MarkMapper;
import com.imt.service.mark.repository.MarkRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class MarkService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MarkService.class);

    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private MeterRegistry meterRegistry;

    private Counter evaluationCounter;

    @PostConstruct
    private void initializeService () {
        this.evaluationCounter = Counter.builder("evaluation")
            .description("Number of evaluation from startup")
            .tag("type", "evaluation")
            .register(meterRegistry);
    }

    private boolean isValidMarkDto (MarkDto dto) {
        if (dto.event() == null || dto.event().id() == null) throw new EventNotFoundException();
        if (dto.participant() == null) throw new ParticipantNotFoundException();
        return true;
    }

    @Transactional
    public MarkDto evaluateEvent (MarkDto dto) {
        LOGGER.debug("Evaluate event: {}", dto);
        this.evaluationCounter.increment();
        isValidMarkDto(dto);
        MarkEntity newMarkEntity = new MarkEntity();
        newMarkEntity.setId(UUID.randomUUID());
        newMarkEntity.setCreationDatetime(LocalDateTime.now());
        newMarkEntity.setEvent(EventMapper.mapToEntity(dto.event()));
        newMarkEntity.setParticipant(dto.participant());
        newMarkEntity.setMarkValue(dto.markValue());
        
        newMarkEntity = markRepository.save(newMarkEntity);
        return MarkMapper.mapToDto(newMarkEntity);
    }

    @Transactional
    public MarkDto updateMark (MarkDto dto) {
        LOGGER.debug("Update mark: {}", dto);
        isValidMarkDto(dto);
        MarkEntity existingMark = markRepository.findById(dto.id())
            .orElseThrow(() -> new MarkNotFoundException());
        existingMark.setMarkValue(dto.markValue());
        existingMark = markRepository.save(existingMark);
        return MarkMapper.mapToDto(existingMark);
    }
}
