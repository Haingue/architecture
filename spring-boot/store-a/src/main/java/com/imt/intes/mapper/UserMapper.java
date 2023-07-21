package com.imt.intes.mapper;

import com.imt.intes.dto.UserDto;
import com.imt.intes.model.UserEntity;

import java.util.Set;

public class UserMapper {

    public static UserDto entityToDto (UserEntity entity) {
        UserDto dto = new UserDto(
                entity.getLogin(),
                "******",
                entity.getAddress(),
                entity.isEnable(),
                entity.getRoles(),
                Set.of()
        );
        return dto;
    }

    public static UserDto entityToDtoAnonymized (UserEntity entity) {
        UserDto dto = new UserDto(
                entity.getLogin(),
                null,
                null,
                false,
                null,
                null
        );
        return dto;
    }

    public static UserEntity dtoToEntity (UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setLogin(dto.login());
        entity.setPassword(dto.password());
        entity.setAddress(dto.address());
        entity.setEnable(dto.isEnable());
        entity.getRoles().addAll(dto.roles());

        dto.orders().stream().map(OrderMapper::dtoToEntity).forEach(orderEntity -> entity.getOrders().add(orderEntity));

        return entity;
    }
}
