package com.imt.services.joboffer.exceptions;

public class NotFound extends RuntimeException {
    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }
}
