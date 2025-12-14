package com.haingue.tp1.CommunityBookstore.mapper;

import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book model);
    Book toModel(BookDto dto);

}
