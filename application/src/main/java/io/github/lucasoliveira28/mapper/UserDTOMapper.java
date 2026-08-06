package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.domain.User;
import io.github.lucasoliveira28.dto.request.UserRequestDTO;
import io.github.lucasoliveira28.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", expression = "java(java.math.BigDecimal.valueOf(userRequestDTO.getBalance()))")
    User toUser(UserRequestDTO userRequestDTO);

}
