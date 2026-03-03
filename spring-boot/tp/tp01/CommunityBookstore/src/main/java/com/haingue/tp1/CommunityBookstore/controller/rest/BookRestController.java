package com.haingue.tp1.CommunityBookstore.controller.rest;

import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import com.haingue.tp1.CommunityBookstore.model.Role;
import com.haingue.tp1.CommunityBookstore.service.crud.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/book")
public class BookRestController {

    private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<BookDto>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    ) {
        return ResponseEntity.ok(bookService.searchAll(title, author, pageNumber, pageSize));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getOneBook(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.findOneByIsbn(isbn));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto, Authentication authentication) {
        logger.debug("Create book: {} [by={}]", bookDto, authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.create(bookDto));
    }

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, Authentication authentication) {
        logger.debug("Update book: {} [by={}]", bookDto, authentication.getPrincipal());
        return ResponseEntity.ok(bookService.update(bookDto));
    }

    @DeleteMapping("/{isbn}")
    @PreAuthorize("SERVICE")
    public ResponseEntity<BookDto> deleteBook(@PathVariable String isbn, Authentication authentication) {
        logger.debug("Delete book: {} [by={}]", isbn, authentication.getPrincipal());
        bookService.delete(isbn);
        return ResponseEntity.ok().build();
    }
}
