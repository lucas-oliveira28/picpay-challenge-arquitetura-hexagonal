package io.github.lucasoliveira28.controller;

import io.github.lucasoliveira28.dto.request.UserRequestDTO;
import io.github.lucasoliveira28.dto.response.UserResponseDTO;
import io.github.lucasoliveira28.mapper.UserDTOMapper;
import io.github.lucasoliveira28.port.input.UserCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserDTOMapper userDTOMapper;
    private final UserCommand userCommand;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        var user = userCommand.createUser(userDTOMapper.toUser(userRequestDTO));
        var userResponseDTO = userDTOMapper.toUserResponseDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") String userId) {
        var user = userCommand.getUserById(UUID.fromString(userId));
        var userResponseDTO = userDTOMapper.toUserResponseDTO(user);
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponseDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userCommand.getAllUsers().stream().map(userDTOMapper::toUserResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        userCommand.deleteUserById(UUID.fromString(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
