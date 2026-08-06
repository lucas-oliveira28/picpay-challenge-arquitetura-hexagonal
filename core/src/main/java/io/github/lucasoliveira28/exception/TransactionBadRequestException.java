package io.github.lucasoliveira28.exception;

public class TransactionBadRequestException extends RuntimeException {
    public TransactionBadRequestException(String message) {
        super(message);
    }
}
