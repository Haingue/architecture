package com.tmmf.chess.api.service.implement;

import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.exception.ChessApiException;
import com.tmmf.chess.api.exception.PlayerCreationException;
import com.tmmf.chess.api.repository.PlayerRepository;
import com.tmmf.chess.api.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final PlayerRepository playerRepository;

    public SubscriptionServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player subscribe(String name) {
        if (playerRepository.existsById(name)) throw new PlayerCreationException("Name already exists");
        if (name.isEmpty()) throw new PlayerCreationException("The user name is not valid");
        Player newPlayer = new Player();
        newPlayer.setUuid(UUID.randomUUID().toString());
        newPlayer.setName(name);

        return playerRepository.save(newPlayer);
    };

    @Override
    public void unsubscription(UUID uuid) {
        Player existingUser = playerRepository.findById(uuid.toString())
                .orElseThrow(ChessApiException::new);
        playerRepository.delete(existingUser);
    }
}
