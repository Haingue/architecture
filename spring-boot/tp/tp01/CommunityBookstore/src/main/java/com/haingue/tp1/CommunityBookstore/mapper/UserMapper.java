package com.haingue.tp1.CommunityBookstore.mapper;

import com.haingue.tp1.CommunityBookstore.dto.UserDto;
import com.haingue.tp1.CommunityBookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User model);
    User toModel(UserDto dto);

}
