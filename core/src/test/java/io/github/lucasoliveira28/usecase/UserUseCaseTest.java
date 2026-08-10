package io.github.lucasoliveira28.usecase;

import io.github.lucasoliveira28.domain.User;
import io.github.lucasoliveira28.domain.enums.UserType;
import io.github.lucasoliveira28.exception.UserBadRequestException;
import io.github.lucasoliveira28.port.output.UserRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    @DisplayName("Deve lançar excessão quando o CPF e/ou email já estiver cadastrado")
    void deveLancarExcecaoQuandoCpfOuEmailJaExistir() {
        User mockUser = new User(UUID.randomUUID(), "Lucas Monteiro", "11481244469", "lucas.monteiro@example.com"
        , "12345", UserType.CLIENT, BigDecimal.valueOf(100));
        when(userRepositoryPort.existsUserByCpf("11481244469")
                && userRepositoryPort.existsUserByEmail("lucas.monteiro@example.com"))
                .thenReturn(true);

        UserBadRequestException exception = assertThrows(UserBadRequestException.class, () -> userUseCase.createUser(mockUser));

        assertEquals("Email ou CPF já cadastrado", exception.getMessage());
        verify(userRepositoryPort, never()).saveUser(any());

    }

}
