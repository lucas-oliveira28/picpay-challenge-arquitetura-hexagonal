package io.github.lucasoliveira28.port.input;

import io.github.lucasoliveira28.domain.Transaction;

import java.util.UUID;

public interface TransactionCommand {

    Transaction newTransaction(UUID payerId, UUID payeeId, Double ammount);
    Transaction getTransactionById(UUID transactionId);

}
