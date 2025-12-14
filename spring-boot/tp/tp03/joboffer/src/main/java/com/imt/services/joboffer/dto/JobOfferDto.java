package com.imt.services.joboffer.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record JobOfferDto (
        UUID id,
        String title,
        String description,
        UUID studentId,
        CompanyDto owner,
        LocalDate startDate,
        LocalDate endDate,
        LocalTime startDayTime,
        LocalTime endDayTime,
        int expirationDays,
        Instant creationTimestamp
) {
}
