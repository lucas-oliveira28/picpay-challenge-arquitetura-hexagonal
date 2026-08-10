package io.github.lucasoliveira28.adapter;

import io.github.lucasoliveira28.TestJpaConfig;
import io.github.lucasoliveira28.domain.User;
import io.github.lucasoliveira28.domain.enums.UserType;
import io.github.lucasoliveira28.mapper.UserMapper;
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
public class UserRepositoryAdapterTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private UserRepositoryAdapter userRepositoryAdapter;

    @BeforeEach
    void setUp() {
        userRepositoryAdapter = new UserRepositoryAdapter(userRepository, userMapper);
    }

    @Test
    @DisplayName("Deve salvar um usuário no banco e recuperar com sucesso")
    void deveSalvarERecuperarUsuarioComSucesso() {

        User domainUser = new User(
                null,
                "Lucas Monteiro",
                "11481244469",
                "lucas@example.com",
                "1234567",
                UserType.CLIENT,
                BigDecimal.valueOf(100.00));

        User savedUser = userRepositoryAdapter.saveUser(domainUser);
        Optional<User> foundUser = userRepositoryAdapter.getUserById(savedUser.getId());

        assertNotNull(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("Lucas Monteiro", foundUser.get().getFullName());
        assertEquals(0, BigDecimal.valueOf(100.00).compareTo(foundUser.get().getBalance()));
    }

    @Test
    @DisplayName("Deve retornar Optional vazio quando o ID do usuário não existir")
    void deveRetornarVazioQuandoUsuarioNaoExiste() {

        Optional<User> result = userRepositoryAdapter.getUserById(UUID.randomUUID());

        assertTrue(result.isEmpty());
    }

}
