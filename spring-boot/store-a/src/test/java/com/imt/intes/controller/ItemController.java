package com.imt.intes.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imt.intes.model.ItemEntity;
import com.imt.intes.repository.ItemRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemController {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Order(1)
    void saveNewItem () throws Exception {
        ItemEntity item = new ItemEntity();
        item.setName("ItemRestController");
        item.setWeight(84.0);
        item.setPrice(42.0);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MvcResult result = mvc.perform(post("/service/item")
                .content(mapper.writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        ItemEntity resultItem = mapper.readValue(result.getResponse().getContentAsString(), ItemEntity.class);
        Assert.notNull(resultItem, "The result item is null");
        Assert.isTrue(resultItem.getId() > 0, "The result item id is not set");
        Assert.isTrue(item.getName().equals(resultItem.getName()), "The name is not the same between item an result item");
        Assert.isTrue(item.getWeight() == resultItem.getWeight(), "The weight is not the same between item an result item");
        Assert.isTrue(item.getPrice() == resultItem.getPrice(), "The price is not the same between item an result item");
    }

    @Test
    @Order(2)
    void selectItem () throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MvcResult result = mvc.perform(get("/service/item/search/ItemRestController"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        Page<ItemEntity> resultItem = mapper.readValue(result.getResponse().getContentAsString(), Page.class);
        Assert.notNull(resultItem, "The result item is null");
        Assert.isTrue(!resultItem.isEmpty() && resultItem.stream().anyMatch(item -> item.getName().equals("ItemRestController")), "No result item with name: ItemRestController");
    }

    @Test
    @Order(3)
    void updateItem () throws Exception {
        Optional<ItemEntity> itemResult = itemRepository.findFirstByName("ItemRestController");
        Assert.isTrue(itemResult.isPresent(), "No item found with name ItemRestController");
        ItemEntity item = itemResult.get();
        item.setWeight(4.0);
        item.setPrice(10.0);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MvcResult result = mvc.perform(put("/service/item")
                        .content(mapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        ItemEntity resultItem = mapper.readValue(result.getResponse().getContentAsString(), ItemEntity.class);
        Assert.notNull(resultItem, "The result item is null");

        itemResult = itemRepository.findFirstByName("ItemRestController");
        Assert.isTrue(itemResult.isPresent(), "No item found with name ItemRestController");
        ItemEntity updatedItem = itemResult.get();
        Assert.isTrue(item.getWeight() == updatedItem.getWeight(), "The weight is not the same between item an result item");
        Assert.isTrue(item.getPrice() == updatedItem.getPrice(), "The price is not the same between item an result item");
    }

    @Test
    @Order(4)
    void deleteItem () throws Exception {
        Optional<ItemEntity> itemResult = itemRepository.findFirstByName("ItemRestController");
        Assert.isTrue(itemResult.isPresent(), "No item found with name ItemRestController");
        ItemEntity item = itemResult.get();

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MvcResult result = mvc.perform(delete("/service/item")
                        .content(mapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        itemResult = itemRepository.findFirstByName("ItemRestController");
        Assert.isTrue(!itemResult.isPresent(), "Item found with name ItemRestController");
    }
}
