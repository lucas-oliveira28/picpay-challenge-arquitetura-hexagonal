package io.github.lucasoliveira28.adapter;

import io.github.lucasoliveira28.TestJpaConfig;
import io.github.lucasoliveira28.domain.Transaction;
import io.github.lucasoliveira28.mapper.TransactionMapper;
import io.github.lucasoliveira28.repository.TransactionRepository;
import io.github.lucasoliveira28.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestJpaConfig.class)
public class TransactionRepositoryAdapterTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    private TransactionRepositoryAdapter transactionRepositoryAdapter;

    @BeforeEach
    void setUp() {
        transactionRepositoryAdapter = new TransactionRepositoryAdapter(userRepository, transactionRepository, transactionMapper);
    }

    @Test
    @DisplayName("Deve salvar um Transaction no banco e recuperar com sucesso")
    void deveSalvarERecuperarTransactionComSucesso(){

        Transaction domainTransaction = new Transaction(
                null,
                BigDecimal.valueOf(100.00),
                UUID.randomUUID(),
                UUID.randomUUID());

        Transaction savedTransaction = transactionRepositoryAdapter.saveTransaction(domainTransaction);
        Optional<Transaction> optionalTransaction = transactionRepositoryAdapter.getTransactionById(savedTransaction.getId());

        assertNotNull(savedTransaction.getId());
        assertTrue(optionalTransaction.isPresent());
        assertEquals(savedTransaction.getAmount(), optionalTransaction.get().getAmount());
        assertEquals(savedTransaction.getPayerId(), optionalTransaction.get().getPayerId());
        assertEquals(savedTransaction.getPayeeId(), optionalTransaction.get().getPayeeId());
    }

    @Test
    @DisplayName("Deve retornar Optional vazio quando o ID da transaction não existir")
    void deveRetornarVazioQuandoTransactionNaoExiste() {

        Optional<Transaction> result = transactionRepositoryAdapter.getTransactionById(UUID.randomUUID());

        assertTrue(result.isEmpty());
    }
}
