package com.haingue.tp1.CommunityBookstore.service.crud;

import com.haingue.tp1.CommunityBookstore.dto.UserDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public interface UserService {
    UserDto create(@Nonnull UserDto userDto);
    UserDto update(@Nonnull UserDto userDto);
    void delete(@Nonnull UUID uuid);
    UserDto findOneById(@Nonnull UUID uuid);
    PaginatedResponseDto<UserDto> findAll(int pageNumber, int pageSize);
}
