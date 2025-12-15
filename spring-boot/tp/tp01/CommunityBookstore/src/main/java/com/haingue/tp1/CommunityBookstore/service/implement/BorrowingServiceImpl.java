package com.haingue.tp1.CommunityBookstore.service.implement;

import com.haingue.tp1.CommunityBookstore.dto.BorrowingDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import com.haingue.tp1.CommunityBookstore.exception.BadRequestException;
import com.haingue.tp1.CommunityBookstore.exception.BookNotAvailableException;
import com.haingue.tp1.CommunityBookstore.mapper.BorrowingMapper;
import com.haingue.tp1.CommunityBookstore.model.Book;
import com.haingue.tp1.CommunityBookstore.model.Borrowing;
import com.haingue.tp1.CommunityBookstore.model.User;
import com.haingue.tp1.CommunityBookstore.repository.BookRepository;
import com.haingue.tp1.CommunityBookstore.repository.BorrowingRepository;
import com.haingue.tp1.CommunityBookstore.repository.UserRepository;
import com.haingue.tp1.CommunityBookstore.service.BorrowingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingServiceImpl.class);

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BorrowingDto borrowBook(String isbn, UUID customerUuid) {
        User customer = userRepository.findById(customerUuid)
                .orElseThrow(BadRequestException::new);
        Book book = bookRepository.findById(isbn)
                .orElseThrow(BadRequestException::new);
        if (!book.isAvailable()) throw new BookNotAvailableException(isbn);
        Borrowing borrowing = new Borrowing();
        borrowing.setUuid(UUID.randomUUID());
        borrowing.setBook(book);
        borrowing.setCustomer(customer);
        borrowing.setBorrowingDate(Instant.now());
        borrowing = borrowingRepository.save(borrowing);

        book.setAvailable(false);
        bookRepository.save(book);
        logger.info("Book borrowed: {}", book);
        return BorrowingMapper.INSTANCE.toDto(borrowing);
    }

    @Override
    public void returnBook(String isbn, UUID customerUuid) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(BadRequestException::new);
        book.setAvailable(true);
        bookRepository.save(book);

        Optional<Borrowing> borrowingResult = borrowingRepository.findFirstByBookIsbnAndCustomerUuid(isbn, customerUuid);
        if (borrowingResult.isPresent()) {
            Borrowing borrowing = borrowingResult.get();
            borrowing.setReturnDate(Instant.now());
            borrowingRepository.save(borrowing);
        }
        logger.info("Book returned: {}", book);
    }

    @Override
    public void returnBook(UUID borrowingUuid) {
        Borrowing borrowing = borrowingRepository.findById(borrowingUuid)
                .orElseThrow(BadRequestException::new);
        borrowing.setReturnDate(Instant.now());
        borrowingRepository.save(borrowing);
        Book book = borrowing.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
        logger.info("Book returned by id: {}", book);
    }

    @Override
    public boolean isBorrowed(String isbn) {
        return bookRepository.findById(isbn).map(Book::isAvailable).orElse(false);
    }

    @Override
    public PaginatedResponseDto<BorrowingDto> getBorrowings(int page, int size) {
        Page<Borrowing> result = borrowingRepository.findAll(PageRequest.of(page, size));
        return PaginatedResponseDto.toPaginatedDto(result, BorrowingMapper.INSTANCE::toDto);
    }

    @Override
    public PaginatedResponseDto<BorrowingDto> getUserBorrowings(UUID customerUuid, int page, int size) {
        Page<Borrowing> result = borrowingRepository.findAllByCustomerUuid(customerUuid, PageRequest.of(page, size));
        return PaginatedResponseDto.toPaginatedDto(result, BorrowingMapper.INSTANCE::toDto);
    }
}
