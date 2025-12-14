package com.haingue.tp1.CommunityBookstore.dto;

import com.haingue.tp1.CommunityBookstore.model.Role;

import java.util.UUID;

public record UserDto (
    UUID uuid,
    String name,
    String email,
    String password,
    Role role
) {}
