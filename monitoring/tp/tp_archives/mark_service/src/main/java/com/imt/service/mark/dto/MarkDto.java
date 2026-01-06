package com.imt.service.mark.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MarkDto  (
    UUID id,
    UUID participant,
    EventDto event,
    int markValue,
    LocalDateTime creationDatetime
){}
