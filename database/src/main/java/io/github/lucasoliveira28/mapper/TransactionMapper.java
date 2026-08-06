package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.entity.TransactionEntity;
import io.github.lucasoliveira28.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "payerId", source = ("payer.id"))
    @Mapping(target = "payeeId", source = ("payee.id"))
    Transaction toTransaction(TransactionEntity transaction);

}
