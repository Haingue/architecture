package com.imt.service.mark.constant;

import java.time.LocalDateTime;
import java.util.UUID;

import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.entity.MarkEntity;
import com.imt.service.mark.mapper.MarkMapper;

public enum MarkExemples {
    
    Mark_1 (UUID.randomUUID(), UserExemples.User_1.getId(), EventExemples.Event_1.toDto(), 3, LocalDateTime.now());

    private UUID id;
    private UUID participant;
    private EventDto event;
    private int markValue;
    private LocalDateTime creationDatetime;

    private MarkExemples(UUID id, UUID participant, EventDto event, int markValue, LocalDateTime creationDatetime) {
        this.id = id;
        this.participant = participant;
        this.event = event;
        this.markValue = markValue;
        this.creationDatetime = creationDatetime;
    }

    public MarkDto toDto () {
        return new MarkDto(
            id,
            participant,
            event,
            markValue,
            creationDatetime
        );
    }

    public MarkEntity toEntity () {
        return MarkMapper.mapToEntity(this.toDto());
    }
    
}
