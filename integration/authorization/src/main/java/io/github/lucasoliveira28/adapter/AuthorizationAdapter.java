package io.github.lucasoliveira28.adapter;

import io.github.lucasoliveira28.operation.AuthorizationOperation;
import io.github.lucasoliveira28.port.output.AuthorizationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthorizationAdapter implements AuthorizationCommand {

    private final AuthorizationOperation authorizationOperation;

    @Override
    public Boolean getAuthorization() {
        return authorizationOperation.getAuthorization();
    }

}
