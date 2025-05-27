package com.tmmf.chess.api.service;

import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.entity.Score;

import java.util.List;
import java.util.UUID;

public interface ScoringService {

    Player declareScore (UUID playerId, Score score);
    List<Player> getTopPlayers(int number);

}
