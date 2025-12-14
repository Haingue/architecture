package com.haingue.tp1.CommunityBookstore.controller.rest;

import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import com.haingue.tp1.CommunityBookstore.service.crud.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            @RequestParam(required = false) Integer pageSize) {
        return ResponseEntity.ok(bookService.searchAll(title, author, pageNumber, pageSize));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getOneBook(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.findOneByIsbn(isbn));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        logger.debug("Create book: {}", bookDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.create(bookDto));
    }

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        logger.debug("Update book: {}", bookDto);
        return ResponseEntity.ok(bookService.update(bookDto));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable String isbn) {
        logger.debug("Delete book: {}", isbn);
        bookService.delete(isbn);
        return ResponseEntity.ok().build();
    }
}
