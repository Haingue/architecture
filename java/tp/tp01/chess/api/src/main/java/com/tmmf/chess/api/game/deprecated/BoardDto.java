package com.tmmf.chess.api.game.deprecated;

import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.core.game.Board;

import java.util.List;

public record BoardDto(
        List<PieceDto> pieces,
        Player firstPlayer,
        Player seconPlayer,
        int turn
) {
    public static Board of(BoardDto dto) {
        Board board = new Board();
        //board.getCells().
        return board;
    }
}
