package io.github.lucasoliveira28.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lucasoliveira28.dto.request.TransactionRequestDTO;
import io.github.lucasoliveira28.mapper.TransactionDTOMapper;
import io.github.lucasoliveira28.port.input.TransactionCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransactionCommand transactionCommand;

    @MockitoBean
    private TransactionDTOMapper transactionDTOMapper;

    @Test
    @DisplayName("Deve retornar Status 201 Created quando a requisição de transferência for válida")
    void deveRealizarTransferenciaComSucesso() throws Exception {

        UUID payerId = UUID.randomUUID();
        UUID payeeId = UUID.randomUUID();
        double value = 100.00;

        TransactionRequestDTO requestDTO = new TransactionRequestDTO(payerId.toString(), payeeId.toString(), value);

        mockMvc.perform(post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar Status 400 Bad Request quando o valor for negativo ou nulo")
    void deveRetornarBadRequestQuandoValorInvalido() throws Exception {

        TransactionRequestDTO requestDTO = new TransactionRequestDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(), -10.0);

        mockMvc.perform(post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar Status 400 Bad Request quando o id for inválido")
    void deveRetornarBadRequestQuandoIdInvalido() throws Exception {

        TransactionRequestDTO requestDTO = new TransactionRequestDTO(null, null , 10.0);

        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

}
