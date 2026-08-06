package io.github.lucasoliveira28.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
