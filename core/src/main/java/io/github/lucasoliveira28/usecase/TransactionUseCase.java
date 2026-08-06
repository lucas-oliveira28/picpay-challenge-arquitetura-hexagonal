package io.github.lucasoliveira28.usecase;

import io.github.lucasoliveira28.domain.Transaction;
import io.github.lucasoliveira28.domain.User;
import io.github.lucasoliveira28.domain.enums.UserType;
import io.github.lucasoliveira28.exception.TransactionBadRequestException;
import io.github.lucasoliveira28.exception.TransactionNotAuthorizedException;
import io.github.lucasoliveira28.exception.TransactionNotFoundException;
import io.github.lucasoliveira28.exception.UserNotFoundException;
import io.github.lucasoliveira28.port.input.TransactionCommand;
import io.github.lucasoliveira28.port.output.AuthorizationCommand;
import io.github.lucasoliveira28.port.output.NotificationCommand;
import io.github.lucasoliveira28.port.output.TransactionRepositoryPort;
import io.github.lucasoliveira28.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class TransactionUseCase implements TransactionCommand {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final AuthorizationCommand authorizationCommand;
    private final NotificationCommand notificationCommand;


    @Override
    public Transaction newTransaction(UUID payerId, UUID payeeId, Double ammount) {
        if (ammount == null || ammount.isNaN() || ammount.isInfinite()) {
            throw new TransactionBadRequestException("Valor de transferência não pode ser nulo");
        } else if (ammount <= 0) {
            throw new TransactionBadRequestException("Valor da transferência tem que ser maior que 0");
        }

        Optional<User> payer = userRepositoryPort.getUserById(payerId);
        Optional<User> payee = userRepositoryPort.getUserById(payeeId);

        if (payer.isEmpty()) {
            throw new UserNotFoundException("Payer não encontrado");
        } else if (payee.isEmpty()) {
            throw new UserNotFoundException("Payee não encontrado");
        }

        if (payer.get().getUserType().equals(UserType.MERCHANT)) {
            throw new TransactionBadRequestException("Lojista não podem realizar pagamentos");
        }

        if (BigDecimal.valueOf(ammount).compareTo(payer.get().getBalance()) > 0) {
            throw new TransactionBadRequestException("Dinheiro insuficiente");
        }

        if (!authorizationCommand.getAuthorization()) {
            throw new TransactionNotAuthorizedException("Transação não autorizada");
        }

        payer.get().setBalance(payer.get().getBalance().subtract(BigDecimal.valueOf(ammount)));
        payee.get().setBalance(payee.get().getBalance().add(BigDecimal.valueOf(ammount)));

        userRepositoryPort.saveUser(payer.get());
        userRepositoryPort.saveUser(payee.get());

        notificationCommand.sendNotification();

        return transactionRepositoryPort.saveTransaction(new Transaction(null, BigDecimal.valueOf(ammount), payerId, payeeId));
    }

    @Override
    public Transaction getTransactionById(UUID transactionId) {
        Optional<Transaction> transaction = transactionRepositoryPort.getTransactionById(transactionId);
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new TransactionNotFoundException("Transação não encontrada");
        }
    }
}
