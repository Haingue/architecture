package com.imt.service.mark.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.entity.MarkEntity;
import com.imt.service.mark.mapper.EventMapper;
import com.imt.service.mark.mapper.MarkMapper;
import com.imt.service.mark.repository.MarkRepository;

@Service
public class MarkService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MarkService.class);

    @Autowired
    private MarkRepository markRepository;

    public MarkDto evaluateEvent (MarkDto dto) {
        MarkEntity newMarkEntity = new MarkEntity();
        newMarkEntity.setId(UUID.randomUUID());
        newMarkEntity.setCreationDatetime(LocalDateTime.now());

        if (dto.event() == null || dto.event().id() == null) throw new RuntimeException("No event defined");
        newMarkEntity.setEvent(EventMapper.mapToEntity(dto.event()));
        if (dto.participant() == null) throw new RuntimeException("No participant defined");
        newMarkEntity.setParticipant(dto.participant());
        newMarkEntity.setMarkValue(dto.markValue());
        
        newMarkEntity = markRepository.save(newMarkEntity);
        return MarkMapper.mapToDto(newMarkEntity);
    }
}
