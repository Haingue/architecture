package com.tmmf.chess.core.game;

public record Point (int lineIdx, int columnIdx) {
    @Override
    public String toString() {
        return "Point{" +
                "lineIdx=" + lineIdx +
                ", columnIdx=" + columnIdx +
                '}';
    }
}
