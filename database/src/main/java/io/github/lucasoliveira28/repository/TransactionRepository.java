package io.github.lucasoliveira28.repository;

import io.github.lucasoliveira28.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}
