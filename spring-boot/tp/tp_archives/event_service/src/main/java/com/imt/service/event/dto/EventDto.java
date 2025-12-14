package com.imt.service.event.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDto (
    UUID id,
    String title,
    String description,
    double ticketPrice,
    UUID organizer,
    LocationDto location,
    LocalDateTime datetime
){}