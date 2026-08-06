package io.github.lucasoliveira28.operation;

import feign.FeignException;
import io.github.lucasoliveira28.api.AuthorizationAPI;
import io.github.lucasoliveira28.exception.TransactionNotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationOperation {

    private final AuthorizationAPI api;

    public Boolean getAuthorization() {
        try {
            return api.authorize().getData().getAuthorization();
        } catch (FeignException.Forbidden e) {
            throw new TransactionNotAuthorizedException("Transação não autorizada");
        }
    }
}
