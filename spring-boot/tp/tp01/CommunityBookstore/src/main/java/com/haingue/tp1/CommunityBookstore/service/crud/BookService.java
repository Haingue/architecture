package com.haingue.tp1.CommunityBookstore.service.crud;

import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface BookService {
    BookDto create(@Nonnull BookDto newBookDto);
    BookDto update(@Nonnull BookDto bookDto);
    void delete(@Nonnull String isbn);
    BookDto findOneByIsbn(@Nonnull String isbn);
    PaginatedResponseDto<BookDto> getAll(@Nullable Integer pageNumber, @Nullable Integer pageSize);
    PaginatedResponseDto<BookDto> searchAll(@Nullable String titleRegex, @Nullable String authorRegex, @Nullable Integer pageNumber, @Nullable Integer pageSize);
}
