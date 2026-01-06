package com.imt.service.mark.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record EventDto (
    UUID id,
    String title,
    UUID organizer,
    LocalDateTime creationDatetime
    // ,Set<MarkDto> marks
){}
