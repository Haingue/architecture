package com.tmmf.chess.core.piece;

import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.game.Point;

import java.util.Set;

public abstract class Piece {
    private final Color color;
    private boolean alive;
    private boolean transFormed;

    public Piece(Color color) {
        this.color = color;
        this.alive = true;
        this.transFormed = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isTransFormed() {
        return transFormed;
    }

    public void setTransFormed(boolean transFormed) {
        this.transFormed = transFormed;
    }

    public Color getColor() {
        return color;
    }

    public abstract Set<Point> getAvailableMoves(Point sourcePoint, Board board);

}
