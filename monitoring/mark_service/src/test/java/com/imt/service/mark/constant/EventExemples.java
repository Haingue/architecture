package com.imt.service.mark.constant;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.entity.EventEntity;
import com.imt.service.mark.mapper.EventMapper;

public enum EventExemples {
    
    Event_1 (UUID.randomUUID(), "Event test 1", UserExemples.User_1.getId(), LocalDateTime.now(), Set.of());

    private UUID id;
    private String title;
    private UUID organizer;
    private LocalDateTime creationDatetime;
    private Set<MarkDto> marks;

    private EventExemples(UUID id, String title, UUID organizer, LocalDateTime creationDatetime, Set<MarkDto> marks) {
        this.id = id;
        this.title = title;
        this.organizer = organizer;
        this.creationDatetime = creationDatetime;
        this.marks = marks;
    }

    public EventDto toDto () {
        return new EventDto(
            id,
            title,
            organizer,
            creationDatetime,
            marks
        );
    }

    public EventEntity toEntity () {
        return EventMapper.mapToEntity(this.toDto());
    }
    
}
