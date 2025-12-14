package com.haingue.tp1.CommunityBookstore.exception;

public class BadRequestException extends BusinessException {
    public BadRequestException() {
        super("Bad request");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
