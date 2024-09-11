package com.imt.service.event.service;

import java.util.List;
import java.util.UUID;

import com.imt.service.event.dto.EventDto;

public interface EventService {
    
    public EventDto organizedEvent (EventDto newEvent);
    public EventDto cancelEvent (EventDto event);
    public EventDto updateEvent (EventDto event);

    public EventDto findEvent (UUID id);
    public List<EventDto> findAllEvent ();
    public List<EventDto> findAllEvent (int page, int number);

}
