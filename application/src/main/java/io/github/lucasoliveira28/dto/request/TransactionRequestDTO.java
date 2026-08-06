package io.github.lucasoliveira28.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDTO {

    @NotNull(message = "O id do payer é necessário")
    @JsonProperty("payerId")
    private String payerId;

    @NotNull(message = "O id do payee é necessário")
    @JsonProperty("payeeId")
    private String payeeId;

    @NotNull(message = "O valor não pode ser nulo")
    @JsonProperty("amount")
    private Double amount;

}
