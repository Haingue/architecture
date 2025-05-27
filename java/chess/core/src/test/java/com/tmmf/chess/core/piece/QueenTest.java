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

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.getCells().replaceAll(p -> Optional.empty());
        board.setPiece(4,4, new Queen(Color.WHITE));
    }

    @Test
    void shouldHaveQueen() {
        assertTrue(board.getCell(4,4).isPresent());
        assertInstanceOf(Queen.class, board.getCell(4, 4).get());
    }

    @Test
    void shouldHavePossibleMoves() throws PieceNotFoundException {
        Set<Point> availableMoves = board.getAvailableMoves(new Point(4, 4));
        Assertions.assertEquals(27, availableMoves.size());


        board.setPiece(4,3, new Pawn(Color.WHITE));
        board.setPiece(4,5, new Pawn(Color.BLACK));
        availableMoves = board.getAvailableMoves(new Point(4, 4));
        Assertions.assertEquals(21, availableMoves.size());
    }

}