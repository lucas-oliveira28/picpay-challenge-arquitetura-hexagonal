package io.github.lucasoliveira28.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lucasoliveira28.dto.request.UserRequestDTO;
import io.github.lucasoliveira28.mapper.UserDTOMapper;
import io.github.lucasoliveira28.port.input.UserCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserDTOMapper userDTOMapper;

    @MockitoBean
    private UserCommand userCommand;

    @Test
    @DisplayName("Deve retornar STATUS 201 Created quando a requisição de criação de usuário for válida")
    void deveCriarUsuarioComSucesso() throws Exception {

        UserRequestDTO requestDTO = new UserRequestDTO("Lucas Monteiro", "11481244469",
                "lucas@example.com", "123456", "CLIENT", 100.00);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar STATUS 400 Bad Request quando o CPF for inválido")
    void deveRetornarBadRequestQuandoCpfInvalido() throws Exception {

        UserRequestDTO requestDTO = new UserRequestDTO("Lucas Monteiro", "12345678900",
                "lucas@example.com", "123456", "CLIENT", 100.00);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

}
