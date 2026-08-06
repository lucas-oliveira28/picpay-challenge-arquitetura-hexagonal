package io.github.lucasoliveira28.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    private UUID id;
    private BigDecimal amount;
    private UUID payerId;
    private UUID payeeId;

}
