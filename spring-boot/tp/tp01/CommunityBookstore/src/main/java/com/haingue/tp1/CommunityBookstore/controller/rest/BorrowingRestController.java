package com.haingue.tp1.CommunityBookstore.controller.rest;

import com.haingue.tp1.CommunityBookstore.dto.BorrowingDto;
import com.haingue.tp1.CommunityBookstore.dto.BorrowingRequestDto;
import com.haingue.tp1.CommunityBookstore.dto.ReturnRequestDto;
import com.haingue.tp1.CommunityBookstore.service.BorrowingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/service/borrowing")
public class BorrowingRestController {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingRestController.class);

    private final BorrowingService borrowingService;

    public BorrowingRestController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping
    public ResponseEntity<BorrowingDto> borrowingBook(@RequestBody BorrowingRequestDto borrowingRequestDto) {
        logger.info("Borrowing book: {}", borrowingRequestDto);
        return ResponseEntity.ok(borrowingService.borrowBook(borrowingRequestDto.bookIsbn(), borrowingRequestDto.customerUuid()));
    }

    @PutMapping
    public ResponseEntity<BorrowingDto> returnBook(@RequestBody ReturnRequestDto returnRequestDto) {
        logger.info("Return book: {}", returnRequestDto);
        borrowingService.returnBook(returnRequestDto.bookIsbn(), returnRequestDto.customerUuid());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{borrowingUuid}/return")
    public ResponseEntity<BorrowingDto> returnBook(@PathVariable UUID borrowingUuid) {
        logger.info("Return book by uuid: {}", borrowingUuid);
        borrowingService.returnBook(borrowingUuid);
        return ResponseEntity.ok().build();
    }
}
