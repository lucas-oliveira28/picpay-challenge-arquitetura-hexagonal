package io.github.lucasoliveira28.adapter;

import io.github.lucasoliveira28.entity.TransactionEntity;
import io.github.lucasoliveira28.entity.UserEntity;
import io.github.lucasoliveira28.mapper.TransactionMapper;
import io.github.lucasoliveira28.repository.TransactionRepository;
import io.github.lucasoliveira28.repository.UserRepository;
import io.github.lucasoliveira28.domain.Transaction;
import io.github.lucasoliveira28.port.output.TransactionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        UserEntity payer = userRepository.findById(transaction.getPayerId()).orElse(null);
        UserEntity payee = userRepository.findById(transaction.getPayeeId()).orElse(null);
        TransactionEntity transactionEntity = new TransactionEntity(null, transaction.getAmount(), payer, payee);
        var savedTransaction = transactionRepository.save(transactionEntity);
        return transactionMapper.toTransaction(savedTransaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(UUID transactionId) {
        Optional<TransactionEntity> transactionEntity = transactionRepository.findById(transactionId);
        if (transactionEntity.isPresent()) {
            return transactionEntity.map(transactionMapper::toTransaction);
        } else {
            return Optional.empty();
        }
    }

}
