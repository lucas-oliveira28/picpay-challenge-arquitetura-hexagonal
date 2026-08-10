package io.github.lucasoliveira28.usecase;

import io.github.lucasoliveira28.domain.User;
import io.github.lucasoliveira28.exception.UserBadRequestException;
import io.github.lucasoliveira28.exception.UserNotFoundException;
import io.github.lucasoliveira28.port.input.UserCommand;
import io.github.lucasoliveira28.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase implements UserCommand {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User createUser(User user) {
        if (userRepositoryPort.existsUserByCpf(user.getCpf()) ||  userRepositoryPort.existsUserByEmail(user.getEmail())) {
            throw new UserBadRequestException("Email ou CPF já cadastrado");
        }
        return userRepositoryPort.saveUser(user);
    }

    @Override
    public User getUserById(UUID userId) {
        Optional<User> user = userRepositoryPort.getUserById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.getAllUsers();
    }

    @Override
    public void deleteUserById(UUID userId) {
        Optional<User> user = userRepositoryPort.getUserById(userId);
        if (user.isPresent()) {
            userRepositoryPort.deleteUserById(userId);
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }
}
