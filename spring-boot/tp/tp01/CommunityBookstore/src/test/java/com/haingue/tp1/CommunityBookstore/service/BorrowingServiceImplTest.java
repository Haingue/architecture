package com.haingue.tp1.CommunityBookstore.service;

import com.haingue.tp1.CommunityBookstore.dto.BorrowingDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import com.haingue.tp1.CommunityBookstore.exception.BadRequestException;
import com.haingue.tp1.CommunityBookstore.exception.BookNotAvailableException;
import com.haingue.tp1.CommunityBookstore.model.Book;
import com.haingue.tp1.CommunityBookstore.model.Borrowing;
import com.haingue.tp1.CommunityBookstore.model.Role;
import com.haingue.tp1.CommunityBookstore.model.User;
import com.haingue.tp1.CommunityBookstore.repository.BookRepository;
import com.haingue.tp1.CommunityBookstore.repository.BorrowingRepository;
import com.haingue.tp1.CommunityBookstore.repository.UserRepository;
import com.haingue.tp1.CommunityBookstore.service.implement.BorrowingServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingServiceImplTest {

    @Mock
    private BorrowingRepository borrowingRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BorrowingServiceImpl borrowingService;


    @Test
    public void shouldBorrowBook_Success() {
        // Arrange
        String isbn = "1234567890";
        UUID customerUuid = UUID.randomUUID();
        User customer = new User(customerUuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER);
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), true);
        Borrowing borrowing = new Borrowing();
        borrowing.setUuid(UUID.randomUUID());
        borrowing.setBook(book);
        borrowing.setCustomer(customer);
        borrowing.setBorrowingDate(Instant.now());

        when(userRepository.findById(customerUuid)).thenReturn(Optional.of(customer));
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        when(borrowingRepository.save(any(Borrowing.class))).thenReturn(borrowing);

        // Act
        BorrowingDto result = borrowingService.borrowBook(isbn, customerUuid);

        // Assert
        assertNotNull(result);
        verify(bookRepository, times(1)).save(book);
        verify(borrowingRepository, times(1)).save(any(Borrowing.class));
    }

    @Test
    public void shouldBorrowBook_UserNotFound_ThrowsBadRequestException() {
        // Arrange
        String isbn = "1234567890";
        UUID customerUuid = UUID.randomUUID();
        when(userRepository.findById(customerUuid)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> borrowingService.borrowBook(isbn, customerUuid));
    }

    @Test
    public void shouldBorrowBook_BookNotFound_ThrowsBadRequestException() {
        // Arrange
        String isbn = "1234567890";
        UUID customerUuid = UUID.randomUUID();
        User customer = new User(customerUuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER);
        when(userRepository.findById(customerUuid)).thenReturn(Optional.of(customer));
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> borrowingService.borrowBook(isbn, customerUuid));
    }

    @Test
    public void shouldBorrowBook_BookNotAvailable_ThrowsBadRequestException() {
        // Arrange
        String isbn = "1234567890";
        UUID customerUuid = UUID.randomUUID();
        User customer = new User(customerUuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER);
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), false);
        when(userRepository.findById(customerUuid)).thenReturn(Optional.of(customer));
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        // Act & Assert
        assertThrows(BookNotAvailableException.class, () -> borrowingService.borrowBook(isbn, customerUuid));
    }

    @Test
    public void shouldReturnBook_Success() {
        // Arrange
        String isbn = "1234567890";
        UUID customerUuid = UUID.randomUUID();
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), false);
        Borrowing borrowing = new Borrowing();
        borrowing.setUuid(UUID.randomUUID());
        borrowing.setBook(book);
        borrowing.setCustomer(new User(customerUuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER));

        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        when(borrowingRepository.findFirstByBookIsbnAndCustomerUuid(isbn, customerUuid)).thenReturn(Optional.of(borrowing));

        // Act
        borrowingService.returnBook(isbn, customerUuid);

        // Assert
        verify(bookRepository, times(1)).save(book);
        verify(borrowingRepository, times(1)).save(borrowing);
    }

    @Test
    public void shouldReturnBook_BookNotFound_ThrowsBadRequestException() {
        // Arrange
        String isbn = "1234567890";
        UUID customerUuid = UUID.randomUUID();
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> borrowingService.returnBook(isbn, customerUuid));
    }

    @Test
    public void shouldReturnBookById_Success() {
        // Arrange
        UUID borrowingUuid = UUID.randomUUID();
        Borrowing borrowing = new Borrowing();
        borrowing.setUuid(borrowingUuid);
        borrowing.setBook(new Book("1234567890", "Title", "Author", LocalDate.now(), false));
        when(borrowingRepository.findById(borrowingUuid)).thenReturn(Optional.of(borrowing));

        // Act
        borrowingService.returnBook(borrowingUuid);

        // Assert
        verify(borrowingRepository, times(1)).save(borrowing);
        verify(bookRepository, times(1)).save(borrowing.getBook());
    }

    @Test
    public void shouldReturnBookById_BorrowingNotFound_ThrowsBadRequestException() {
        // Arrange
        UUID borrowingUuid = UUID.randomUUID();
        when(borrowingRepository.findById(borrowingUuid)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> borrowingService.returnBook(borrowingUuid));
    }

    @Test
    public void shouldIsBorrowed_BookNotFound_ReturnsFalse() {
        // Arrange
        String isbn = "1234567890";
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        // Act
        boolean result = borrowingService.isBorrowed(isbn);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldIsBorrowed_BookAvailable_ReturnsFalse() {
        // Arrange
        String isbn = "1234567890";
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), true);
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        // Act
        boolean result = borrowingService.isBorrowed(isbn);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldIsBorrowed_BookNotAvailable_ReturnsTrue() {
        // Arrange
        String isbn = "1234567890";
        Book book = new Book(isbn, "Title", "Author", LocalDate.now(), false);
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        // Act
        boolean result = borrowingService.isBorrowed(isbn);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldIsBorrowed_BookNotFound() {
        // Arrange
        String isbn = "1234567890";

        // Act
        boolean result = borrowingService.isBorrowed(isbn);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldGetBorrowings_Success() {
        // Arrange
        Page<Borrowing> borrowings = new PageImpl<>(Collections.singletonList(new Borrowing()));
        when(borrowingRepository.findAll(any(PageRequest.class))).thenReturn(borrowings);

        // Act
        PaginatedResponseDto<BorrowingDto> result = borrowingService.getBorrowings(0, 5);

        // Assert
        assertNotNull(result);
        assertFalse(result.content().isEmpty());
    }

    @Test
    public void shouldGetUserBorrowings_Success() {
        // Arrange
        UUID customerUuid = UUID.randomUUID();
        Page<Borrowing> borrowings = new PageImpl<>(Collections.singletonList(new Borrowing()));
        when(borrowingRepository.findAllByCustomerUuid(eq(customerUuid), any(PageRequest.class))).thenReturn(borrowings);

        // Act
        PaginatedResponseDto<BorrowingDto> result = borrowingService.getUserBorrowings(customerUuid, 0, 5);

        // Assert
        assertNotNull(result);
        assertFalse(result.content().isEmpty());
    }
}