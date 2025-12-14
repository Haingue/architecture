package com.haingue.tp1.CommunityBookstore.service;

import com.haingue.tp1.CommunityBookstore.dto.BorrowingDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;

import java.util.UUID;

public interface BorrowingService {

    BorrowingDto borrowBook(String isbn, UUID customerUuid);
    void returnBook(String isbn, UUID customerUuid);
    void returnBook(UUID borrowingUuid);

    boolean isBorrowed(String isbn);

    PaginatedResponseDto<BorrowingDto> getBorrowings(int page, int size);
    PaginatedResponseDto<BorrowingDto> getUserBorrowings(UUID customerUuid, int page, int size);
}
