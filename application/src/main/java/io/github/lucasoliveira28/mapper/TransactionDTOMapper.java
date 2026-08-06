package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.domain.Transaction;
import io.github.lucasoliveira28.dto.response.TransactionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionDTOMapper {

    TransactionResponseDTO toTransactionResponseDTO(Transaction transaction);

}
