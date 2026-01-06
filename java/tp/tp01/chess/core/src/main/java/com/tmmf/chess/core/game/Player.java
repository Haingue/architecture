package com.tmmf.chess.core.game;

public class Player {

    public static final int TIMER_VALUE = 1_000 * 60 * 5;

    private final Color color;
    private final String name;
    private long timer;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
        this.resetTimer();
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public long getTimer() {
        return timer;
    }

    public void resetTimer() {
        this.timer = TIMER_VALUE;
    }
}
