package com.imt.intes.mapper;

import com.imt.intes.dto.OrderDto;
import com.imt.intes.model.OrderEntity;
import com.imt.intes.model.id.OrderId;

public class OrderMapper {

    public static OrderDto entityToDto (OrderEntity entity) {
        OrderDto dto = new OrderDto(
                entity.getId().getCreationDatetime(),
                entity.getId().getBuyerLogin(),
                entity.getId().getItemId(),
                entity.getQuantity(),
                entity.getDeliveryDate()
        );
        return dto;
    }

    public static OrderEntity dtoToEntity (OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setId(dtoToEntityId(dto));
        entity.setQuantity(dto.quantity());
        entity.setDeliveryDate(dto.deliveryDate());
        return entity;
    }

    public static OrderId dtoToEntityId (OrderDto dto) {
        OrderId entityId = new OrderId();
        entityId.setCreationDatetime(dto.creationDatetime());
        entityId.setBuyerLogin(dto.buyerLogin());
        entityId.setItemId(dto.itemId());
        return entityId;
    }

}
