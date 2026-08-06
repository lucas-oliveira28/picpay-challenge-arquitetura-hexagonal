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
public class TransactionResponseDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("payerId")
    private String payerId;

    @JsonProperty("payeeId")
    private String payeeId;

}
