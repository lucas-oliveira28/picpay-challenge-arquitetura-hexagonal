package io.github.lucasoliveira28.domain;

import io.github.lucasoliveira28.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private UUID id;
    private String fullName;
    private String cpf;
    private String email;
    private String password;
    private UserType userType;
    private BigDecimal balance;

}
