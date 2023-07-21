package com.imt.intes.repository;

import com.imt.intes.model.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void wellInsert () {
        UserEntity user1 = new UserEntity();
        user1.setLogin("pierreD");
        user1.setPassword("TEST");
        user1.setAddress("Address 1");
        Assert.notNull(user1.getOrders(), "The orders collection is null after the user instantiation");
        user1 = userRepository.save(user1);
        Assert.notNull(user1, "The user is null");
        Assert.isTrue(userRepository.findById(user1.getLogin()).isPresent(), "The user is not correctly saved");
    }

    @Test
    @Order(2)
    void badInsert () {
        Assertions.assertThrows(Exception.class, () -> {
            userRepository.save(new UserEntity());
        }, "The check on nullable constraint has failed");
        Assertions.assertThrows(Exception.class, () -> {
            UserEntity user2 = new UserEntity();
            user2.setLogin("paulD");
            user2.setPassword(null);
            userRepository.save(user2);
        }, "The check on nullable constraint has failed");
    }

    @Test
    @Order(3)
    void selectByName () {
        Optional<UserEntity> user1Result = userRepository.findById("pierreD");
        Assert.notNull(user1Result, "The optional user1 is null");
        Assert.isTrue(user1Result.isPresent(), "The user1 is not found");
        UserEntity user1 = user1Result.get();
        Assert.notNull(user1.getAddress(), "The address of user1 is null");
        Assert.isTrue(user1.getAddress().equals("Address 1"), "The address of user1 is not correct");
    }

    @Test
    @Order(4)
    void update () {
        Optional<UserEntity> user1Result = userRepository.findById("pierreD");
        Assert.notNull(user1Result, "The optional user1 is null");
        Assert.isTrue(user1Result.isPresent(), "The user1 is not found");
        UserEntity user1 = user1Result.get();
        user1.setPassword("Test 2");
        user1.setAddress("Address 2");
        user1 = userRepository.save(user1);
        Assert.notNull(user1.getPassword(), "The password of user1 is null");
        Assert.isTrue(user1.getPassword().equals("Test 2"), "The password of user1 is not correct");
        Assert.notNull(user1.getAddress(), "The address of user1 is null");
        Assert.isTrue(user1.getAddress().equals("Address 2"), "The address of user1 is not correct");
    }

    @Test
    @Order(5)
    void delete () {
        Optional<UserEntity> user1Result = userRepository.findById("pierreD");
        Assert.isTrue(user1Result.isPresent(), "The user1 is not found");
        userRepository.delete(user1Result.get());
        user1Result = userRepository.findById("pierreD");
        Assert.isTrue(!user1Result.isPresent(), "The user1 is found after deletion");
    }

}
