package com.haingue.tp1.CommunityBookstore.service.crud;

import com.haingue.tp1.CommunityBookstore.dto.UserDto;
import jakarta.annotation.Nonnull;

import java.util.UUID;

public interface UserService {
    UserDto create(@Nonnull UserDto userDto);
    UserDto update(@Nonnull UserDto userDto);
    UserDto findOneById(@Nonnull UUID uuid);
}
