package com.imt.intes.service;

import com.imt.intes.exeption.ItemNotFoundException;
import com.imt.intes.exeption.UserNotFoundException;
import com.imt.intes.model.ItemEntity;
import com.imt.intes.model.OrderEntity;
import com.imt.intes.model.UserEntity;
import com.imt.intes.model.id.OrderId;
import com.imt.intes.repository.OrderRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    Page<OrderEntity> findAll (Pageable page) {
        // TODO
        throw new NotYetImplementedException();
    }

    Page<OrderEntity> findAllByUser (UserEntity user, Pageable page) {
        // TODO
        throw new NotYetImplementedException();
    }

    Page<OrderEntity> findAllByItem (ItemEntity item, Pageable page) {
        // TODO
        throw new NotYetImplementedException();
    }
    
    public OrderEntity orderItem (String buyerLogin, long itemId, int quantity, LocalDate deliveryDate) {
        logger.debug("Order {} items (id={}) by user (login={})", quantity, itemId, buyerLogin);
        UserEntity buyerUserEntity = userService.findOneByLogin(buyerLogin)
                .orElseThrow(() -> new UserNotFoundException());
        ItemEntity itemEntity = itemService.findOneItem(itemId)
                .orElseThrow(() -> new ItemNotFoundException());

        OrderId orderId = new OrderId();
        orderId.setBuyerLogin(buyerLogin);
        orderId.setItemId(itemId);
        orderId.setCreationDatetime(LocalDateTime.now());
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setQuantity(quantity);
        orderEntity.setDeliveryDate(deliveryDate);
        orderEntity.setItem(itemEntity);
        orderEntity.setBuyer(buyerUserEntity);

        orderEntity = orderRepository.save(orderEntity);
//        buyerUserEntity.getOrders().add(orderEntity);
//        itemEntity.getOrders().add(orderEntity);
        return orderEntity;
    }

}
