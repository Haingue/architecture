package com.haingue.tp1.CommunityBookstore.mapper;

import com.haingue.tp1.CommunityBookstore.dto.BorrowingDto;
import com.haingue.tp1.CommunityBookstore.model.Borrowing;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class, BookMapper.class})
public interface BorrowingMapper {

    BorrowingMapper INSTANCE = Mappers.getMapper(BorrowingMapper.class);

    BorrowingDto toDto(Borrowing model);
    Borrowing toModel(BorrowingDto dto);

}
