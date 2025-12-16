package com.haingue.tp1.CommunityBookstore.service.crud;

import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import com.haingue.tp1.CommunityBookstore.exception.BadRequestException;
import com.haingue.tp1.CommunityBookstore.mapper.BookMapper;
import com.haingue.tp1.CommunityBookstore.model.Book;
import com.haingue.tp1.CommunityBookstore.repository.BookRepository;
import com.haingue.tp1.CommunityBookstore.service.crud.implement.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void shouldFindAllBooks() {
        when(bookRepository.findAll(PageRequest.of(0, 10))).thenReturn(Page.empty());
        assertTrue(bookService.getAll(0, 10).isEmpty());
    }


    @Test
    public void shouldCreateBook_Success() {
        // Arrange
        BookDto newBookDto = new BookDto("1234567890", "Title", "Author", LocalDate.now(), true);
        Book book = BookMapper.INSTANCE.toModel(newBookDto);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        BookDto result = bookService.create(newBookDto);

        // Assert
        assertNotNull(result);
        assertEquals(newBookDto.isbn(), result.isbn());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void shouldCreateBook_InvalidInput_ThrowsBadRequestException() {
        // Arrange
        BookDto invalidBookDto = new BookDto("", null, null, LocalDate.now(), false);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> bookService.create(invalidBookDto));
    }

    @Test
    public void shouldUpdateBook_Success() {
        BookDto bookDto = new BookDto("1234567890", "Title", "Author", LocalDate.now(), true);
        Book book = BookMapper.INSTANCE.toModel(bookDto);
        when(bookRepository.existsById(bookDto.isbn())).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto result = bookService.update(bookDto);

        assertNotNull(result);
        assertEquals(bookDto.isbn(), result.isbn());
        verify(bookRepository, times(1)).existsById(bookDto.isbn());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void shouldUpdateBook_NotFound_ThrowsBadRequestException() {
        // Arrange
        BookDto bookDto = new BookDto("1234567890", "Title", "Author", LocalDate.now(), true);
        when(bookRepository.existsById(bookDto.isbn())).thenReturn(false);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> bookService.update(bookDto));
    }

    @Test
    public void shouldDeleteBook_Success() {
        // Arrange
        String isbn = "1234567890";
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), true);
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        // Act
        assertDoesNotThrow(() -> bookService.delete(isbn));

        // Assert
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    public void shouldDeleteBook_NotFound_ThrowsBadRequestException() {
        // Arrange
        String isbn = "1234567890";
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> bookService.delete(isbn));
    }

    @Test
    public void shouldDeleteBook_NotAvailable_ThrowsBadRequestException() {
        String isbn = "1234567890";
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), false);
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        assertThrows(BadRequestException.class, () -> bookService.delete(isbn));
    }

    @Test
    public void shouldFindOneByIsbn_Success() {
        String isbn = "1234567890";
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), true);
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        BookDto result = bookService.findOneByIsbn(isbn);

        assertNotNull(result);
        assertEquals(isbn, result.isbn());
    }

    @Test
    public void shouldFindOneByIsbn_NotFound_ThrowsBadRequestException() {
        String isbn = "1234567890";
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> bookService.findOneByIsbn(isbn));
    }

    @Test
    public void shouldGetAllBooks_Success() {
        Page<Book> bookPage = new PageImpl<>(Collections.singletonList(new Book("1234567890", "Title", "Author", LocalDate.now(), true)));
        when(bookRepository.findAll(any(PageRequest.class))).thenReturn(bookPage);

        PaginatedResponseDto<BookDto> result = bookService.getAll(0, 5);

        assertNotNull(result);
        assertFalse(result.content().isEmpty());
    }

    @Test
    public void shouldSearchAllBooks_Success() {
        String title = "Title";
        String author = "Author";
        Page<Book> bookPage = new PageImpl<>(Collections.singletonList(new Book("1234567890", title, author, LocalDate.now(), true)));
        when(bookRepository.findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(anyString(), anyString(), any(PageRequest.class))).thenReturn(bookPage);

        PaginatedResponseDto<BookDto> result = bookService.searchAll(title, author, 0, 5);

        assertNotNull(result);
        assertFalse(result.content().isEmpty());
    }
}