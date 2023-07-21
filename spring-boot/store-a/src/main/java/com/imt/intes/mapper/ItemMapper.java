package com.imt.intes.mapper;

import com.imt.intes.dto.ItemDto;
import com.imt.intes.model.ItemEntity;

public class ItemMapper {

    public static ItemDto entityToDto (ItemEntity entity) {
        ItemDto dto = new ItemDto(
                entity.getId(),
                entity.getName(),
                entity.getWeight(),
                entity.getPrice()
        );
        return dto;
    }

    public static ItemEntity dtoToEntity (ItemDto dto) {
        ItemEntity entity = new ItemEntity();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setPrice(dto.price());
        entity.setWeight(dto.weight());
        return entity;
    }

}
