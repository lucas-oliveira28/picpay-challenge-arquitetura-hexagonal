package io.github.lucasoliveira28.usecase;

import io.github.lucasoliveira28.domain.Transaction;
import io.github.lucasoliveira28.domain.User;
import io.github.lucasoliveira28.domain.enums.UserType;
import io.github.lucasoliveira28.port.output.AuthorizationCommand;
import io.github.lucasoliveira28.port.output.NotificationCommand;
import io.github.lucasoliveira28.port.output.TransactionRepositoryPort;
import io.github.lucasoliveira28.port.output.UserRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionUseCaseTest {

    @Mock
    private TransactionRepositoryPort transactionRepositoryPort;
    @Mock
    private UserRepositoryPort userRepositoryPort;
    @Mock
    private AuthorizationCommand authorizationCommand;
    @Mock
    private NotificationCommand notificationCommand;

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    @Test
    @DisplayName("Deve realizar a transferência com sucesso quando todos os dados e serviços forem válidos")
    void deveRealizarTransferenciaComSucesso() {

        UUID payerId = UUID.randomUUID();
        UUID payeeId = UUID.randomUUID();
        double amount = 100.00;

        User payer = new User(payerId, "Lucas", "12345678900", "lucas@email.com", "1234", UserType.CLIENT, BigDecimal.valueOf(500.00));
        User payee = new User(payeeId, "Lojista", "98765432100", "loja@email.com", "1234", UserType.MERCHANT, BigDecimal.valueOf(1000.00));

        when(userRepositoryPort.getUserById(payerId)).thenReturn(Optional.of(payer));
        when(userRepositoryPort.getUserById(payeeId)).thenReturn(Optional.of(payee));
        when(authorizationCommand.getAuthorization()).thenReturn(true);
        Transaction transactionSalva = new Transaction(UUID.randomUUID(), BigDecimal.valueOf(amount), payer.getId(), payeeId);
        when(transactionRepositoryPort.saveTransaction(any(Transaction.class))).thenReturn(transactionSalva);

        Transaction result = transactionUseCase.newTransaction(payerId, payeeId, amount);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(400.00), payer.getBalance());
        assertEquals(BigDecimal.valueOf(1100.00), payee.getBalance());

        verify(userRepositoryPort, times(1)).saveUser(payer);
        verify(userRepositoryPort, times(1)).saveUser(payee);
        verify(transactionRepositoryPort, times(1)).saveTransaction(any());
        verify(notificationCommand, times(1)).sendNotification();

    }

}
