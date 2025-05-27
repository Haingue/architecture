package com.tmmf.chess.api.game;

import com.tmmf.chess.core.game.Point;

public record MoveDto(String gameCode, Point from, Point to) {
}
