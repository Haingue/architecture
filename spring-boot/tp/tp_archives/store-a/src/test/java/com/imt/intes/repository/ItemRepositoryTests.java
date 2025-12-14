package com.imt.intes.repository;

import com.imt.intes.model.ItemEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemRepositoryTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Order(1)
    void wellInsert () {
        ItemEntity item1 = new ItemEntity();
        item1.setName("Test 1");
        item1.setPrice(10.0);
        item1 = itemRepository.save(item1);
        Assert.notNull(item1, "The item is null");
        Assert.isTrue(item1.getId() > 0, "The id is not incremented");
    }

    @Test
    @Order(2)
    void badInsert () {
        Assertions.assertThrows(Exception.class, () -> {
            ItemEntity item = new ItemEntity();
            item.setPrice(10.0);
            item.setWeight(0.5);
            itemRepository.save(item);
        }, "The check on nullable constraint has failed");
        Assertions.assertThrows(Exception.class, () -> {
            ItemEntity item = new ItemEntity();
            item.setName("Test 1");
            itemRepository.save(item);
        }, "The check on unique constraint has failed");
    }

    @Test
    @Order(3)
    void selectByName () {
        Optional<ItemEntity> item1Result = itemRepository.findFirstByName("Test 1");
        Assert.notNull(item1Result, "The optional item1 is null");
        Assert.isTrue(item1Result.isPresent(), "The item1 is not found");
        ItemEntity item1 = item1Result.get();
        Assert.isTrue(item1.getName().equals("Test 1"), "The name of item1 is not correct");
        Assert.isTrue(item1.getPrice() == 10.0, "The price of item1 is not correct");
        Assert.isTrue(item1.getWeight() == 0, "The weight of item1 is not correct");
    }

    @Test
    @Order(4)
    void update () {
        Optional<ItemEntity> item1Result = itemRepository.findFirstByName("Test 1");
        Assert.isTrue(item1Result.isPresent(), "The item1 is not found");
        ItemEntity item1 = item1Result.get();
        item1.setPrice(15.0);
        item1 = itemRepository.save(item1);
        Assert.notNull(item1, "The item is null after the update");
        Assert.isTrue(item1.getName().equals("Test 1"), "The name of item1 is not correct");
        Assert.isTrue(item1.getPrice() == 15.0, "The price of item1 is not correct");
        Assert.isTrue(item1.getWeight() == 0, "The weight of item1 is not correct");
    }

    @Test
    @Order(5)
    void delete () {
        Optional<ItemEntity> item1Result = itemRepository.findFirstByName("Test 1");
        Assert.isTrue(item1Result.isPresent(), "The item1 is not found");
        itemRepository.delete(item1Result.get());
        item1Result = itemRepository.findFirstByName("Test 1");
        Assert.isTrue(!item1Result.isPresent(), "The item1 is found after deletion");
    }

}
