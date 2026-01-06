package com.tmmf.chess.core.piece;

import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.game.Point;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }
    @Override
    public Set<Point> getAvailableMoves(Point sourcePoint, Board board) {
        Set<Point> moves = new HashSet<>();

        int[][] knightMoves = {
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };

        for (int[] move : knightMoves) {
            int line = sourcePoint.lineIdx() + move[0];
            int column = sourcePoint.columnIdx() + move[1];

            Point newPoint = new Point(line, column);

            if (board.isInside(newPoint)) {
                Optional<Piece> pieceTarget = board.getPiece(newPoint);

                if (pieceTarget.isEmpty() || pieceTarget.get().getColor() != this.getColor()) {
                    moves.add(newPoint);
                }
            }
        }

        return moves;
    }
}
