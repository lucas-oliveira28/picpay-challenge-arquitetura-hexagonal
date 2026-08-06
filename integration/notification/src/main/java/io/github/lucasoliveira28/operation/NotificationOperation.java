package io.github.lucasoliveira28.operation;

import io.github.lucasoliveira28.api.NotificationAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationOperation {

    private final NotificationAPI api;

    public void sendNotification() {
        try {
            ResponseEntity<Void> resp = api.notification();
            if (resp.getStatusCode().is2xxSuccessful()) {
                System.out.println("Notificação enviada com sucesso!");
            } else {
                System.out.println("Falha no envio da notificação");
            }
        } catch (Exception e) {
            System.out.println("Falha no envio da notificação");
        }
    }
}
