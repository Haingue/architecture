package com.imt.service.event.constant;

import java.time.LocalDateTime;
import java.util.UUID;

import com.imt.service.event.dto.EventDto;
import com.imt.service.event.dto.LocationDto;
import com.imt.service.event.entity.EventEntity;
import com.imt.service.event.mapper.EventMapper;

public enum EventExemples {
    
    Event_1 (UUID.randomUUID(), "Event test 1", "Super description 1", 11.0, UUID.randomUUID(), LocationExemples.Location_1.toDto(), LocalDateTime.now()),
    Event_2 (UUID.randomUUID(), "Event test 2", "Super description 2", 12.0, UUID.randomUUID(), LocationExemples.Location_1.toDto(), LocalDateTime.now()),
    Event_3 (UUID.randomUUID(), "Event test 3", "Super description 3", 13.0, UUID.randomUUID(), LocationExemples.Location_2.toDto(), LocalDateTime.now()),
    Event_4 (UUID.randomUUID(), "Event test 4", "Super description 4", 14.0, UUID.randomUUID(), LocationExemples.Location_2.toDto(), LocalDateTime.now());

    private UUID id;
    private String title;
    private String description;
    private double ticketPrice;
    private UUID organizer;
    private LocationDto location;
    private LocalDateTime datetime;

    private EventExemples(UUID id, String title, String description, double ticketPrice, UUID organizer,
        LocationDto location, LocalDateTime datetime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.organizer = organizer;
        this.location = location;
        this.datetime = datetime;
    }

    public EventDto toDto () {
        return new EventDto(
            id,
            title,
            description,
            ticketPrice,
            organizer,
            location,
            datetime
        );
    }

    public EventEntity toEntity () {
        return EventMapper.mapToEntity(this.toDto());
    }
    
}
