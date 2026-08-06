package io.github.lucasoliveira28.adapter;

import io.github.lucasoliveira28.entity.UserEntity;
import io.github.lucasoliveira28.mapper.UserMapper;
import io.github.lucasoliveira28.repository.UserRepository;
import io.github.lucasoliveira28.domain.User;
import io.github.lucasoliveira28.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User saveUser(User user) {
        var userEntity = userMapper.toUserEntity(user);
        var savedUser = userRepository.save(userEntity);
        return userMapper.toUser(savedUser);
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            return userEntity.map(userMapper::toUser);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUser).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userRepository.existsUserEntitiesByEmail(email);
    }

    @Override
    public Boolean existsUserByCpf(String cpf) {
        return userRepository.existsUserEntitiesByCpf(cpf);
    }
}
