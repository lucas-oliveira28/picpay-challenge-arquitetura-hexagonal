package io.github.lucasoliveira28.port.output;

import io.github.lucasoliveira28.domain.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepositoryPort {

    Transaction saveTransaction(Transaction transaction);
    Optional<Transaction> getTransactionById(UUID transactionId);

}
