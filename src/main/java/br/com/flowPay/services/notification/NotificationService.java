package br.com.flowPay.services.notification;

import br.com.flowPay.dto.notification.NotificationMessage;
import br.com.flowPay.model.customer.CustomerRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;

    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void queueRequest(CustomerRequest request, int priority) {
        NotificationMessage message = new NotificationMessage(
                "REQUEST_QUEUED",
                "",
                request.getId(),
                String.format(
                        "A solicitação %s foi enfileirada. Tipo: %s, Descrição: %s.",
                        request.getId(),
                        request.getRequestType(),
                        request.getDescription()
                )
        );

        rabbitTemplate.convertAndSend("support.notifications.exchange", "notifications.new.request", message, m -> {
            m.getMessageProperties().setPriority(priority);
            return m;
        });
        System.out.printf("Solicitação %s colocada na fila e notificada.%n", request.getId());
    }
}
