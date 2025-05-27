package com.tmmf.chess.api.service.implement;

import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.entity.Score;
import com.tmmf.chess.api.exception.ChessApiException;
import com.tmmf.chess.api.repository.PlayerRepository;
import com.tmmf.chess.api.service.ScoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoringServiceImpl implements ScoringService {

    private final Logger LOGGER = LoggerFactory.getLogger(ScoringServiceImpl.class);

    private final PlayerRepository playerRepository;

    public ScoringServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player declareScore(UUID playerId, Score score) {
        LOGGER.debug("Declare score for player '{}'", playerId);

        Player player = this.playerRepository.findById(playerId.toString()).orElseThrow(ChessApiException::new);
        player.getScores().add(score);
        playerRepository.save(player);

        return player;
    }

    @Override
    public List<Player> getTopPlayers(int number) {
        LOGGER.debug("Get top players from score list");
        Map<Player, Integer> scoresPerPlayer = new HashMap<>();
        this.playerRepository.findAll() // Attention au findAll !
                .forEach(player ->
                        scoresPerPlayer.put(player, player.getScores().stream().mapToInt(Score::getValue).sum()));

        return scoresPerPlayer.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .limit(number).toList();
    }

}
