package com.imt.intes.repository;

import com.imt.intes.model.ItemEntity;
import com.imt.intes.model.OrderEntity;
import com.imt.intes.model.UserEntity;
import com.imt.intes.model.id.OrderId;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    private UserEntity prepareUserEntity () {
        UserEntity user1 = new UserEntity();
        user1.setLogin("pierreD");
        user1.setPassword("TEST");
        user1.setAddress("Address test");
        user1 = userRepository.save(user1);
        return user1;
    }

    private ItemEntity prepareItemEntity () {
        Optional<ItemEntity> item = itemRepository.findFirstByName("Test order repository");
        ItemEntity item1;
        if (item.isPresent()) {
            return item.get();
        }
        item1 = new ItemEntity();
        item1.setName("Test order repository");
        item1.setPrice(10.0);
        item1 = itemRepository.save(item1);
        return item1;
    }

    @Test
    @Order(1)
    @Transactional
    void wellInsert () {
        UserEntity user1 = new UserEntity();
        user1.setLogin("pierreD");
        user1.setPassword("TEST");
        user1.setAddress("Address test");
        user1 = userRepository.save(user1);

        ItemEntity item1 = prepareItemEntity();

        OrderId orderId = new OrderId();
        orderId.setCreationDatetime(LocalDateTime.now());
        orderId.setItemId(item1.getId());
        orderId.setBuyerLogin(user1.getLogin());

        OrderEntity order = new OrderEntity();
        order.setId(orderId);
        order.setDeliveryDate(LocalDate.now().plus(10, ChronoUnit.DAYS));
        order.setQuantity(5);
        order.setItem(item1);
        order.setBuyer(user1);

        order = orderRepository.
                save(order);
        user1.getOrders().add(order);
        userRepository.save(user1);
        item1.getOrders().add(order);
        itemRepository.save(item1);
        Assert.notNull(order, "The order is null");
        order = orderRepository.findById(orderId).get();
        Assert.notNull(order, "The order is not correctly saved");

        user1 = userRepository.findById(user1.getLogin()).get();
        Assert.isTrue(!user1.getOrders().isEmpty(), "The order is not correctly assigned to the user, user's order list is empty");
        Assert.isTrue(user1.getOrders().stream().anyMatch(orderEntity -> orderEntity.getId().equals(orderId)), "The order is not correctly assigned to the user");
        item1 = itemRepository.findById(item1.getId()).get();
        Assert.isTrue(!item1.getOrders().isEmpty(), "The order is not correctly assigned to the user, item's order list is empty");
        Assert.isTrue(item1.getOrders().stream().anyMatch(orderEntity -> orderEntity.getId().equals(orderId)), "The order is not correctly assigned to the item");
    }

}
