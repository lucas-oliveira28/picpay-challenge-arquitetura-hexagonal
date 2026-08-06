package io.github.lucasoliveira28.repository;

import io.github.lucasoliveira28.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Boolean existsUserEntitiesByCpf(String cpf);
    Boolean existsUserEntitiesByEmail(String email);

}
