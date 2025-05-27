package com.tmmf.chess.api.exception;

public class GameNotFoundException extends ChessApiException {
    public GameNotFoundException(String gameCode) {
        super("Game not found: " + gameCode);
    }
}
