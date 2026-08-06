package io.github.lucasoliveira28.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("email")
    private String email;

    @JsonProperty("userType")
    private String userType;

    @JsonProperty("balance")
    private BigDecimal balance;

}
