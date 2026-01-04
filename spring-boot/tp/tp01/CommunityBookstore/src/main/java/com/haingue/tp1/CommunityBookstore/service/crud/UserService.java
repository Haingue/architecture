package com.haingue.tp1.CommunityBookstore.service.crud;

import com.haingue.tp1.CommunityBookstore.dto.UserDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;

import jakarta.annotation.Nonnull;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    UserDto create(@Nonnull UserDto userDto);
    UserDto update(@Nonnull UserDto userDto);
    void delete(@Nonnull UUID uuid);
    UserDto findOneById(@Nonnull UUID uuid);
    UserDto findFirstByUsername(@Nonnull String username);
    PaginatedResponseDto<UserDto> findAll(int pageNumber, int pageSize);
}
