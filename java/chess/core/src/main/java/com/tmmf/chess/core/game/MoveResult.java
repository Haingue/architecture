package com.tmmf.chess.core.game;

public class MoveResult {

    public static enum Result {MOVE, TAKE, ADJUST, CHECK}

    private Result result;
    private Point source;
    private Point destination;
    private int turn;

    public MoveResult(Point source, Point destination, int turn) {
        this.source = source;
        this.destination = destination;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Point getSource() {
        return source;
    }

    public void setSource(Point source) {
        this.source = source;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "MoveResult{" +
                "result=" + result +
                ", source=" + source +
                ", destination=" + destination +
                '}';
    }
}
