package io.github.lucasoliveira28.adapter;

import io.github.lucasoliveira28.operation.NotificationOperation;
import io.github.lucasoliveira28.port.output.NotificationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationAdapter implements NotificationCommand {

    private final NotificationOperation notificationOperation;

    @Override
    public void sendNotification() {
        notificationOperation.sendNotification();
    }
}
