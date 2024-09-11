package com.imt.service.mark.mapper;

import java.util.stream.Collectors;

import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.entity.EventEntity;

public class EventMapper {
    
    public static EventDto mapToDto (EventEntity entity) {
        return new EventDto(
            entity.getId(),
            entity.getTitle(),
            entity.getOrganizer(),
            entity.getCreationDatetime()
            //, entity.getMarks().stream().map(MarkMapper::mapToDto).collect(Collectors.toSet())
        );
    }

    public static EventEntity mapToEntity (EventDto dto) {
        EventEntity entity = new EventEntity();
        entity.setId(dto.id());
        entity.setTitle(dto.title());
        entity.setOrganizer(dto.organizer());
        entity.setCreationDatetime(dto.creationDatetime());
        // entity.setMarks(dto.marks().stream().map(MarkMapper::mapToEntity).collect(Collectors.toSet()));
        return entity;
    }

}
