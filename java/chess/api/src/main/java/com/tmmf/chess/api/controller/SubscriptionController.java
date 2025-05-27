package com.tmmf.chess.api.controller;

import com.tmmf.chess.api.dto.PlayerDto;
import com.tmmf.chess.api.dto.SubscriptionFormDto;
import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.exception.PlayerCreationException;
import com.tmmf.chess.api.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SubscriptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriptionFormDto subscriptionFormDto) {
        LOGGER.info("New subscription: {}", subscriptionFormDto);
        try {
            Player newPlayer = subscriptionService.subscribe(subscriptionFormDto.name());
            return ResponseEntity.status(HttpStatus.CREATED).body(PlayerDto.to(newPlayer));
        } catch (PlayerCreationException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<?> subscribe(@RequestBody UUID uuid) {
        LOGGER.info("Unsubscription: {}", uuid);
        try {
            subscriptionService.unsubscription(uuid);
            return ResponseEntity.ok().build();
        } catch (PlayerCreationException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
