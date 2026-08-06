package io.github.lucasoliveira28;

import io.github.lucasoliveira28.port.input.TransactionCommand;
import io.github.lucasoliveira28.port.input.UserCommand;
import io.github.lucasoliveira28.port.output.AuthorizationCommand;
import io.github.lucasoliveira28.port.output.NotificationCommand;
import io.github.lucasoliveira28.port.output.TransactionRepositoryPort;
import io.github.lucasoliveira28.port.output.UserRepositoryPort;
import io.github.lucasoliveira28.usecase.TransactionUseCase;
import io.github.lucasoliveira28.usecase.UserUseCase;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.github.lucasoliveira28")
public class AppConfiguration {

    @Bean
    public UserCommand userCommand(UserRepositoryPort userRepositoryPort) {
        return new UserUseCase(userRepositoryPort);
    }

    @Bean
    @Transactional
    public TransactionCommand transactionCommand(TransactionRepositoryPort transactionRepositoryPort, UserRepositoryPort userRepositoryPort, AuthorizationCommand authorizationCommand, NotificationCommand notificationCommand) {
        return new TransactionUseCase(transactionRepositoryPort, userRepositoryPort, authorizationCommand, notificationCommand);
    }

}
