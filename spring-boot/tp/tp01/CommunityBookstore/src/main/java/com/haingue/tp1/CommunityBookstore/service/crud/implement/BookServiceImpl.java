package com.haingue.tp1.CommunityBookstore.service.crud.implement;

import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import com.haingue.tp1.CommunityBookstore.exception.BadRequestException;
import com.haingue.tp1.CommunityBookstore.mapper.BookMapper;
import com.haingue.tp1.CommunityBookstore.model.Book;
import com.haingue.tp1.CommunityBookstore.repository.BookRepository;
import com.haingue.tp1.CommunityBookstore.service.crud.BookService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto create(@Nonnull BookDto newBookDto) {
        if (newBookDto.title() == null || newBookDto.author() == null) throw new BadRequestException();
        if (StringUtils.isEmpty(newBookDto.isbn())) throw new BadRequestException();

        Book book = BookMapper.INSTANCE.toModel(newBookDto);
        book = this.bookRepository.save(book);

        logger.info("New book created: {}", book);
        return BookMapper.INSTANCE.toDto(book);
    }

    @Override
    public BookDto update(@Nonnull BookDto book) {
        if (!this.bookRepository.existsById(book.isbn())) throw new BadRequestException();
        Book bookToUpdate = BookMapper.INSTANCE.toModel(book);
        bookToUpdate = this.bookRepository.save(bookToUpdate);
        logger.info("Book updated: {}", bookToUpdate);
        return BookMapper.INSTANCE.toDto(bookToUpdate);
    }

    @Override
    public void delete(@Nonnull String isbn) {
        Optional<Book> book = this.bookRepository.findById(isbn);
        if (book.isEmpty()) throw new BadRequestException();
        if (!book.get().isAvailable()) throw new BadRequestException();
        this.bookRepository.delete(book.get());
        logger.info("Book deleted: {}", book.get());
    }

    @Override
    public BookDto findOneByIsbn(@Nonnull String isbn) {
        logger.debug("Getting one book: {}", isbn);
        Book book = this.bookRepository.findById(isbn)
                .orElseThrow(BadRequestException::new);
        return BookMapper.INSTANCE.toDto(book);
    }

    @Override
    public PaginatedResponseDto<BookDto> getAll(@Nullable Integer pageNumber, @Nullable Integer pageSize) {
        logger.debug("Getting all books");
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        Page<Book> results = this.bookRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return PaginatedResponseDto.toPaginatedDto(results, BookMapper.INSTANCE::toDto);
    }

    @Override
    public PaginatedResponseDto<BookDto> searchAll(@Nullable String title, @Nullable String author, @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        if (title == null) title = "";
        if (author == null) author = "";
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        logger.debug("Searching all books [title={}, author={}, pageNumber={}, pageSize={}]", title, author, pageNumber, pageSize);
        Page<Book> results = this.bookRepository.findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author, PageRequest.of(pageNumber, pageSize));
        return PaginatedResponseDto.toPaginatedDto(results, BookMapper.INSTANCE::toDto);
    }
}
