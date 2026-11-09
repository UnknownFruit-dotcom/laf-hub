package com.fruit.serverwebapp.exception;

public class AlreadyJoinedException extends RuntimeException {
    public AlreadyJoinedException(String message) {
        super(message);
    }
}
