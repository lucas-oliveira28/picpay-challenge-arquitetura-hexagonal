package io.github.lucasoliveira28.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {

    @NotBlank(message = "O nome deve ser preenchido")
    @Length(min = 7, message = "O nome deve conter mais de 7 caracteres")
    @JsonProperty("fullName")
    private String fullName;

    @CPF(message = "cpf inválido")
    @JsonProperty("cpf")
    private String cpf;

    @Email(message = "email inválido")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "O password deve ser preenchido")
    @Length(min = 6, message = "A senha deve conter mais de 6 caracteres")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "O userType deve ser preenchido")
    @JsonProperty("userType")
    private String userType;

    @NotNull(message = "O balance deve ser preenchido")
    @JsonProperty("balance")
    private Double balance;

}
