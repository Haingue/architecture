package com.tmmf.chess.core.piece;

import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

class PawnTest {

    private Board board = new Board();

    @Test
    void shouldHave2InitialMoves() {
        AtomicReference<Set<Point>> availableMoves = new AtomicReference<>();
        Assertions.assertDoesNotThrow(() -> {
            availableMoves.set(board.getAvailableMoves(new Point(1, 1)));
        });
        Assertions.assertEquals(2, availableMoves.get().size());
    }

}