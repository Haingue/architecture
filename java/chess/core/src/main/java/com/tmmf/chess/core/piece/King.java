package com.tmmf.chess.core.piece;

import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.game.Point;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class King  extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public Set<Point> getAvailableMoves(Point sourcePoint, Board board) {
        Set<Point> moves = new HashSet<>();

        for (int line = -1; line < 2; line++) {
            for (int column = -1; column < 2; column++) {
                Point newPoint = new Point(sourcePoint.lineIdx() + line, sourcePoint.columnIdx() + column);
                if (board.isInside(newPoint) && !sourcePoint.equals(newPoint)) {
                    Optional<Piece> pieceTarget = board.getPiece(newPoint);
                    if (pieceTarget.isEmpty()) moves.add(newPoint);
                    else if (pieceTarget.get().getColor() != this.getColor()) moves.add(newPoint);
                }
            }
        }

        return moves;
    }

}
