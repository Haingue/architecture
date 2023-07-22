package com.imt.intes.controller;

import com.imt.intes.dto.OrderDto;
import com.imt.intes.exeption.ItemNotFoundException;
import com.imt.intes.exeption.UserNotFoundException;
import com.imt.intes.mapper.OrderMapper;
import com.imt.intes.model.OrderEntity;
import com.imt.intes.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity itemOrdering (@RequestBody OrderDto orderDto) {
        logger.info("Order new item");
        try {
            OrderEntity orderEntity = orderService.orderItem(orderDto.buyerLogin(), orderDto.itemId(), orderDto.quantity(), orderDto.deliveryDate());
            return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.entityToDto(orderEntity));
        } catch (ItemNotFoundException | UserNotFoundException exception) {
            return ResponseEntity
                    .badRequest()
                    .header("Error", exception.getMessage())
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .internalServerError()
                    .header("Error", exception.getMessage())
                    .build();
        }
    }
}
