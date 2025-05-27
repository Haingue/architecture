package com.tmmf.chess.api.service;

import com.tmmf.chess.api.dto.SubscriptionFormDto;
import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class SubscriptionServiceTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Test
    void shouldSubscribePlayer() {
        SubscriptionFormDto subscriptionFormDto = new SubscriptionFormDto("Test");
        Player newPlayer = this.subscriptionService.subscribe(subscriptionFormDto.name());
        Assertions.assertNotNull(newPlayer);
        Assertions.assertNotNull(newPlayer.getUuid());
        Assertions.assertEquals(subscriptionFormDto.name(), newPlayer.getName());

        Optional<Player> savedPlayer = playerRepository.findById(newPlayer.getUuid());
        Assertions.assertTrue(savedPlayer.isPresent());
        Assertions.assertEquals(newPlayer.getName(), savedPlayer.get().getName());
    }

    @Test
    void shouldUnsubscribePlayer() {
        Player existingPlayer = this.playerRepository.findFirstByName("Toyoyo 1").get();

        this.subscriptionService.unsubscription(UUID.fromString(existingPlayer.getUuid()));

        Assertions.assertFalse(this.playerRepository.findFirstByName("Toyoyo").isPresent());
        Assertions.assertFalse(this.playerRepository.findById(existingPlayer.getUuid()).isPresent());
    }
}