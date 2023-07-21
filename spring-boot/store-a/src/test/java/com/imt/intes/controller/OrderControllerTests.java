package com.imt.intes.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.imt.intes.dto.OrderDto;
import com.imt.intes.model.ItemEntity;
import com.imt.intes.model.UserEntity;
import com.imt.intes.repository.ItemRepository;
import com.imt.intes.repository.OrderRepository;
import com.imt.intes.repository.UserRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

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
    @WithMockUser(username="user")
    @Order(1)
    void itemOrderingTest () throws Exception {
        UserEntity buyer = prepareUserEntity();
        ItemEntity item = prepareItemEntity();
        OrderDto orderDto = new OrderDto(null, buyer.getLogin(), item.getId(), 10, LocalDate.now().plusDays(20));


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        MvcResult result = mvc.perform(post("/service/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        OrderDto resultOrder = mapper.readValue(result.getResponse().getContentAsString(), OrderDto.class);
        Assert.notNull(resultOrder, "The order result is null");
        Assert.isTrue(resultOrder.itemId() == item.getId(), "The item is not same");
        Assert.isTrue(buyer.getLogin().equals(resultOrder.buyerLogin()), "The buyer is not same");
        Assert.notNull(resultOrder.deliveryDate(), "The delivery date is null");
        Assert.isTrue(orderDto.deliveryDate().equals(resultOrder.deliveryDate()), "The delivery date is not same");
        Assert.isTrue(resultOrder.quantity() == orderDto.quantity(), "The quantity is not the same");
    }

    @Test
    @WithMockUser(username="user")
    @Order(1)
    void should404WhenBadItem () throws Exception {
        UserEntity buyer = prepareUserEntity();
        OrderDto orderDto = new OrderDto(null, buyer.getLogin(), -1L, 10, LocalDate.now().plusDays(20));


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mvc.perform(post("/service/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(orderDto)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(username="user")
    @Order(1)
    void should404WhenBadUser () throws Exception {
        ItemEntity item = prepareItemEntity();
        OrderDto orderDto = new OrderDto(null, "----", item.getId(), 10, LocalDate.now().plusDays(20));


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mvc.perform(post("/service/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(orderDto)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
