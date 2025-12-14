package com.imt.services.joboffer.exceptions;

public class NotAuthorized extends RuntimeException {
    public NotAuthorized(String message) {
        super(message);
    }
}
