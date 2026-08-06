package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.entity.UserEntity;
import io.github.lucasoliveira28.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserEntity user);

    @Mapping(target = "transactionsSent", ignore = true)
    @Mapping(target = "transactionsReceived", ignore = true)
    UserEntity toUserEntity(User user);

}
