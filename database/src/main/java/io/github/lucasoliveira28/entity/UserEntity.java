package io.github.lucasoliveira28.entity;

import io.github.lucasoliveira28.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "balance", precision = 10, scale = 2, nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "payer")
    private Set<TransactionEntity> transactionsSent;

    @OneToMany(mappedBy = "payee")
    private Set<TransactionEntity> transactionsReceived;

}
