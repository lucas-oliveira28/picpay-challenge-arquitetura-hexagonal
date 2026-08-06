package io.github.lucasoliveira28.port.input;

import io.github.lucasoliveira28.domain.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserCommand {

    User createUser(User user);
    User getUserById(UUID userId);
    void deleteUserById(UUID userId);
    List<User> getAllUsers();

}
