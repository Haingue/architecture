package com.imt.service.mark.constant;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.entity.EventEntity;
import com.imt.service.mark.mapper.EventMapper;

public enum EventExemples {
    
    Event_1 (UUID.randomUUID(), "Event test 1", UserExemples.User_1.getId(), LocalDateTime.now(), Set.of()),
    Event_2 (UUID.randomUUID(), "Event test 2", UserExemples.User_1.getId(), LocalDateTime.now(), Set.of()),
    Event_3 (UUID.randomUUID(), "Event test 3", UserExemples.User_1.getId(), LocalDateTime.now(), Set.of()),
    Event_4 (UUID.randomUUID(), "Event test 4", UserExemples.User_1.getId(), LocalDateTime.now(), Set.of());

    public final UUID id;
    public final String title;
    public final UUID organizer;
    public final LocalDateTime creationDatetime;
    public final Set<MarkDto> marks;

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
            creationDatetime
            // ,marks
        );
    }

    public EventEntity toEntity () {
        return EventMapper.mapToEntity(this.toDto());
    }
    
}
