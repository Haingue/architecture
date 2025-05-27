package com.tmmf.chess.core.piece;

import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.game.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.getCells().replaceAll(p -> Optional.empty());
        board.setPiece(4,4, new King(Color.WHITE));
    }

    @Test
    void shouldHaveKing() {
        assertTrue(board.getCell(4,4).isPresent());
        assertInstanceOf(King.class, board.getCell(4, 4).get());
    }

    @Test
    void shouldHavePossibleMoves() throws PieceNotFoundException {
        Set<Point> availableMoves = board.getAvailableMoves(new Point(4, 4));
        Assertions.assertEquals(8, availableMoves.size());

        board.setPiece(4,3, new Pawn(Color.WHITE));
        board.setPiece(4,5, new Pawn(Color.BLACK));
        availableMoves = board.getAvailableMoves(new Point(4, 4));
        Assertions.assertEquals(7, availableMoves.size());
    }
}