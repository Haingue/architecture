package com.haingue.tp1.CommunityBookstore.dto;

import java.util.UUID;

public record BorrowingRequestDto(
    UUID customerUuid,
    String bookIsbn
) {}
