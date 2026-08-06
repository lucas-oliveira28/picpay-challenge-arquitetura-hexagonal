package io.github.lucasoliveira28.controller;

import io.github.lucasoliveira28.dto.request.TransactionRequestDTO;
import io.github.lucasoliveira28.dto.response.TransactionResponseDTO;
import io.github.lucasoliveira28.mapper.TransactionDTOMapper;
import io.github.lucasoliveira28.port.input.TransactionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final TransactionDTOMapper transactionDTOMapper;
    private final TransactionCommand transactionCommand;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponseDTO> newTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        var transaction = transactionCommand.newTransaction(
                UUID.fromString(transactionRequestDTO.getPayerId()),
                UUID.fromString(transactionRequestDTO.getPayeeId()),
                transactionRequestDTO.getAmount());
        var transactionResponseDTO = transactionDTOMapper.toTransactionResponseDTO(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponseDTO);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransaction(@PathVariable("id") String transactionId) {
        var transaction = transactionCommand.getTransactionById(UUID.fromString(transactionId));
        var transactionResponseDTO = transactionDTOMapper.toTransactionResponseDTO(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponseDTO);
    }
}
