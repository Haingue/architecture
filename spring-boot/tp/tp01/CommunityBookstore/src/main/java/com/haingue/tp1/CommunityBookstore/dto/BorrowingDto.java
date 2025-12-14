package com.haingue.tp1.CommunityBookstore.dto;

import java.time.Instant;
import java.util.UUID;

public record BorrowingDto (
    UUID uuid,
    UserDto customer,
    BookDto book,
    Instant borrowingDate,
    Instant returnDate
) {}
