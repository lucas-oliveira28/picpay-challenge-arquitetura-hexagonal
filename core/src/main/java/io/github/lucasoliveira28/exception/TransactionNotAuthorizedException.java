package io.github.lucasoliveira28.exception;

public class TransactionNotAuthorizedException extends RuntimeException {
    public TransactionNotAuthorizedException(String message) {
        super(message);
    }
}
