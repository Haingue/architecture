package com.imt.service.event.service.implement;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.imt.service.event.dto.EventDto;
import com.imt.service.event.entity.EventEntity;
import com.imt.service.event.mapper.EventMapper;
import com.imt.service.event.repository.EventRepository;
import com.imt.service.event.service.EventService;

@Service
public class EventServiceImplement implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImplement.class);

    @Autowired
    private EventRepository eventRepository;

    private boolean isValidEvent (EventDto dto) {
        if (dto.ticketPrice() < 0) throw new RuntimeException("Price not valid");
        if (dto.datetime() == null) throw new RuntimeException("The datetime is missing");
        if (dto.location() == null || dto.location().address() == null) throw new RuntimeException("The location is missing");
        return true;
    }

    @Override
    public EventDto organizedEvent(EventDto newEvent) {
        LOGGER.debug("Try to organize event: {}", newEvent);
        isValidEvent(newEvent);

        EventEntity newEventEntity = EventMapper.mapToEntity(newEvent);
        newEventEntity.setId(UUID.randomUUID());

        newEventEntity = eventRepository.save(newEventEntity);
        return EventMapper.mapToDto(newEventEntity);
    }

    @Override
    public EventDto cancelEvent(EventDto event) {
        EventEntity existingEntity = eventRepository.findById(event.id())
            .orElseThrow(() -> new RuntimeException("Event not found"));
        try {
            // Delete ticket from TicketService
            // TODO

            // Delete event
            eventRepository.delete(existingEntity);
        } catch (Exception exception) {
            LOGGER.error("Error to cancel event: {}", exception);
            throw exception;
        }
        return EventMapper.mapToDto(existingEntity);
    }

    @Override
    public EventDto updateEvent(EventDto event) {
        EventEntity existingEntity = eventRepository.findById(event.id())
            .orElseThrow(() -> new RuntimeException("Event not found"));
        existingEntity.setTitle(event.title());
        existingEntity.setDescription(event.description());
        existingEntity.setTicketPrice(event.ticketPrice());
        existingEntity = eventRepository.save(existingEntity);
        return EventMapper.mapToDto(existingEntity);
    }

    @Override
    public EventDto findEvent(UUID id) {
        EventEntity existingEntity = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        return EventMapper.mapToDto(existingEntity);
    }

    @Override
    public List<EventDto> findAllEvent() {
        return this.findAllEvent(0, 10);
    }

    @Override
    public List<EventDto> findAllEvent(int page, int number) {
        return eventRepository.findAllByOrderByDatetimeDesc(PageRequest.of(page, number))
        .getContent()
        .stream()
        .map(EventMapper::mapToDto)
        .toList();
    }
    
}
