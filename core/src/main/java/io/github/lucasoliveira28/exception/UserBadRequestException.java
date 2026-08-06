package io.github.lucasoliveira28.exception;

public class UserBadRequestException extends RuntimeException {
    public UserBadRequestException(String message) {
        super(message);
    }
}
