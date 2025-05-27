package com.tmmf.chess.api.service;

import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.entity.Score;
import com.tmmf.chess.api.repository.PlayerRepository;
import com.tmmf.chess.api.repository.ScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoringServiceTest {

    @Autowired
    private ScoringService scoringService;
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void shouldReturnPlayers() {
        List<Player> topPlayers = this.scoringService.getTopPlayers(3);
        Assertions.assertFalse(topPlayers.isEmpty());
        Assertions.assertEquals(3, topPlayers.size(), "The Player number is not correct");
    }

    @Test
    void shouldReturnTopPlayers() {
        List<Player> topPlayers = this.scoringService.getTopPlayers(3);

        Player p1 = (Player) topPlayers.toArray()[0];
        int p1Score = p1.getScores().stream().mapToInt(Score::getValue).sum();
        Assertions.assertNotNull(p1, "The Player is not correct");
        Player p2 = (Player) topPlayers.toArray()[2];
        int p2Score = p1.getScores().stream().mapToInt(Score::getValue).sum();
        Assertions.assertNotNull(p2, "The Player is not correct");
        Assertions.assertTrue(p1Score >= p2Score, "The first player is not the best");
    }

    @Test
    void shouldDeclareScore() {
        UUID insertedPlayerUUID = UUID.randomUUID();

        Player insertedPlayer = new Player();
        insertedPlayer.setUuid(insertedPlayerUUID.toString());
        insertedPlayer.setName("Fabini");
        insertedPlayer.setScores(Set.of());

        Score insertedScore = new Score(UUID.randomUUID().toString(), 10, LocalDateTime.now());

        this.playerRepository.save(insertedPlayer);
        this.scoringService.declareScore(insertedPlayerUUID, insertedScore);

        Player player = this.playerRepository.findById(insertedPlayerUUID.toString()).orElse(null);
        assertNotNull(player, "The player is not correct");

        assertTrue(player.getScores().contains(insertedScore), "The player doesn't have the correct score");
    }
}
