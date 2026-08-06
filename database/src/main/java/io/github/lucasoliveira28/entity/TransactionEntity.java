package io.github.lucasoliveira28.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne()
    @JoinColumn(name = "payer_id")
    private UserEntity payer;

    @ManyToOne()
    @JoinColumn(name = "payee_id")
    private UserEntity payee;

}
