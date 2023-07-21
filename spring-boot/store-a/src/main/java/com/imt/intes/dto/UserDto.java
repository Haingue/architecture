package com.imt.intes.dto;

import com.imt.intes.configuration.security.Role;

import java.util.Set;

public record UserDto (
    String login,
    String password,
    String address,
    boolean isEnable,
    Set<Role> roles,
    Set<OrderDto> orders) {

    public boolean isValidLogin () {
        return login != null && !login.isBlank();
    }

}
