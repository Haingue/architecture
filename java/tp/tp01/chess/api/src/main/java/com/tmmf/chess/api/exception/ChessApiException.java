package com.tmmf.chess.api.exception;

public class ChessApiException extends RuntimeException {
    public ChessApiException() {
    }

    public ChessApiException(String message) {
        super(message);
    }

    public ChessApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChessApiException(Throwable cause) {
        super(cause);
    }

    public ChessApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
