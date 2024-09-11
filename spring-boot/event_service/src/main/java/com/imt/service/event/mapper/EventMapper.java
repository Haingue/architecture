package com.imt.service.event.mapper;

import java.util.stream.Collectors;

import com.imt.service.event.dto.EventDto;
import com.imt.service.event.dto.LocationDto;
import com.imt.service.event.entity.EventEntity;

public class EventMapper {
    
    public static EventDto mapToDto (EventEntity entity) {
        LocationDto locationDto = null;
        if (entity.getLocation() != null) locationDto = LocationMapper.mapToDto(entity.getLocation());
        return new EventDto(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getTicketPrice(),
            entity.getOrganizer(),
            locationDto,
            entity.getDatetime()
        );
    }

    public static EventEntity mapToEntity (EventDto dto) {
        EventEntity entity = new EventEntity();
        entity.setId(dto.id());
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setTicketPrice(dto.ticketPrice());
        entity.setOrganizer(dto.organizer());
        entity.setLocation(LocationMapper.mapToEntity(dto.location()));
        entity.setDatetime(dto.datetime());
        return entity;
    }

}
