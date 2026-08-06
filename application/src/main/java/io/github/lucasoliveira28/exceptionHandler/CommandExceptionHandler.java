package io.github.lucasoliveira28.exceptionHandler;

import io.github.lucasoliveira28.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class CommandExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardErrorMessage> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(UserBadRequestException.class)
    public ResponseEntity<StandardErrorMessage> handleUserBadRequestException(UserBadRequestException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(TransactionBadRequestException.class)
    public ResponseEntity<StandardErrorMessage> handleTransactionBadRequestException(TransactionBadRequestException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(TransactionNotAuthorizedException.class)
    public ResponseEntity<StandardErrorMessage> handleTransactionNotAuthorizedException(TransactionNotAuthorizedException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<StandardErrorMessage> handleTransactionNotFoundException(TransactionNotFoundException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                "Bad request error", errors, request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

}
