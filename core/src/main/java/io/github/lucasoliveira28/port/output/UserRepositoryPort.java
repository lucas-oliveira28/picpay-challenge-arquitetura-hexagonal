package io.github.lucasoliveira28.port.output;

import io.github.lucasoliveira28.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    User saveUser(User user);
    Optional<User> getUserById(UUID userId);
    void deleteUserById(UUID userId);
    List<User> getAllUsers();
    Boolean existsUserByEmail(String email);
    Boolean existsUserByCpf(String cpf);

}
