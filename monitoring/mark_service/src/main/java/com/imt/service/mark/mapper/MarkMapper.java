package com.imt.service.mark.mapper;

import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.entity.MarkEntity;

public class MarkMapper {
    
    public static MarkDto mapToDto (MarkEntity entity) {
        EventDto eventDto = null;
        if (entity.getEvent() != null) eventDto = EventMapper.mapToDto(entity.getEvent());
        return new MarkDto(
            entity.getId(),
            entity.getParticipant(),
            eventDto,
            entity.getMarkValue(),
            entity.getCreationDatetime()
        );
    }

    public static MarkEntity mapToEntity (MarkDto dto) {
        MarkEntity entity = new MarkEntity();
        entity.setId(dto.id());
        entity.setParticipant(dto.participant());
        entity.setEvent(EventMapper.mapToEntity(dto.event()));
        entity.setMarkValue(dto.markValue());
        entity.setCreationDatetime(dto.creationDatetime());
        return entity;
    }

}
