package com.tmmf.chess.core.piece;

import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.game.Point;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public Set<Point> getAvailableMoves(Point sourcePoint, Board board) {
        Set<Point> moves = new HashSet<>();

        int[][] directions = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        for (int[] direction : directions) {
            int line = sourcePoint.lineIdx() + direction[0];
            int column = sourcePoint.columnIdx() + direction[1];

            Point newPoint = new Point(line, column);
            while (board.isInside(newPoint)) {
                newPoint = new Point(line, column);
                Optional<Piece> pieceTarget = board.getPiece(newPoint);

                if (pieceTarget.isEmpty()) {
                    moves.add(newPoint);
                } else {
                    if (pieceTarget.get().getColor() != this.getColor()) {
                        moves.add(newPoint);
                    }
                    break;
                }

                line += direction[0];
                column += direction[1];
            }
        }

        return moves;
    }
}
