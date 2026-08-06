package io.github.lucasoliveira28.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorizationDTO {

    private String status;

    private DataDTO data;

    @Data
    public static class DataDTO {
       private Boolean authorization;
    }

}
