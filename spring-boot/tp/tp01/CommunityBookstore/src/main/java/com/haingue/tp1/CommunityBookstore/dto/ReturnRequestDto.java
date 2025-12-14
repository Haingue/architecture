package com.haingue.tp1.CommunityBookstore.dto;

import java.util.UUID;

public record ReturnRequestDto(
    UUID customerUuid,
    String bookIsbn
) {}
