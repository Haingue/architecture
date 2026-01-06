package com.tmmf.chess.api.game;

import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.game.deprecated.PieceDto;
import com.tmmf.chess.core.game.Board;

import java.util.List;

public record GameDto(
        String gameCode,
        Board board
) {}
