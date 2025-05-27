package com.tmmf.chess.core.piece;

import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.game.Point;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Set<Point> getAvailableMoves(Point sourcePoint, Board board) {
        Set<Point> moves = new HashSet<>();

        if (sourcePoint.lineIdx() == 1 || sourcePoint.lineIdx() == Board.BOARD_SIZE - 2) {
            Point newPoint = new Point(sourcePoint.lineIdx() + 2, sourcePoint.columnIdx());
            if (board.getPiece(newPoint).isEmpty()) moves.add(newPoint);
        }
        int direction = board.getTurn() % 2 == 0 ? 1 : -1;
        for (int idx = -1; idx < 2; idx++) {
            Point newPoint = new Point(sourcePoint.lineIdx() + direction, sourcePoint.columnIdx() + idx);
            if (idx == 0 && board.getPiece(newPoint).isEmpty()) moves.add(newPoint);
            Optional<Piece> diagonalPiece = board.getPiece(newPoint);
            if (idx != 0 && diagonalPiece.isPresent() && diagonalPiece.get().getColor() != this.getColor()) moves.add(newPoint);
        }

        return moves;
    }

}
