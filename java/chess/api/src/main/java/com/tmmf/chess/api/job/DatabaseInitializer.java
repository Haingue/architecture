package com.tmmf.chess.api.job;

import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.entity.Score;
import com.tmmf.chess.api.repository.PlayerRepository;
import com.tmmf.chess.api.repository.ScoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private PlayerRepository playerRepository;
    private ScoreRepository scoreRepository;

    public DatabaseInitializer(PlayerRepository playerRepository, ScoreRepository scoreRepository) {
        this.playerRepository = playerRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        for (int idx = 0; idx < 5; idx++) {
            Player player = new Player();
            player.setUuid(UUID.randomUUID().toString());
            player.setName("Toyoyo "+idx);
            player.getScores().addAll(
                    List.of(
                            new Score(UUID.randomUUID().toString(), random.nextInt(20), LocalDateTime.now())
                    )
            );
            // scoreRepository.saveAll(player.getScores());
            playerRepository.save(player);
        }
    }
}
