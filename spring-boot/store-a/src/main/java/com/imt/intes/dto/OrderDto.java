package com.imt.intes.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderDto (
    LocalDateTime creationDatetime,
    String buyerLogin,
    Long itemId,
    Integer quantity,
    LocalDate deliveryDate)
{}