package br.com.flowPay.services.distribution;

import br.com.flowPay.dto.notification.NotificationMessage;
import br.com.flowPay.enums.request.RequestStatus;
import br.com.flowPay.model.customer.CustomerRequest;
import br.com.flowPay.model.supportAgent.SupportAgent;
import br.com.flowPay.repository.CustomerRequestRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequestListenerService {

    private final CustomerRequestRepository requestRepository;
    private final DistributionService distributionService;

    @Autowired
    public RequestListenerService(CustomerRequestRepository requestRepository,
                                  DistributionService distributionService) {
        this.requestRepository = requestRepository;
        this.distributionService = distributionService;
    }

    @RabbitListener(queues = "support.notifications.new.request.queue")
    public void handleNewRequestNotification(NotificationMessage message) {

        CustomerRequest request = requestRepository.findById(message.getRequestId()).orElse(null);
        if (request == null) {
            System.out.println("Solicitação não encontrada: " + message.getRequestId());
            return;
        }

        SupportAgent agent =  distributionService.findAvailableAgent(request.getRequestType());

        if (agent != null) {
            request.setStatus(RequestStatus.IN_PROGRESS);
            request.setSupportAgent(agent);
            requestRepository.save(request);
            System.out.printf("Agente %s já tem 3 solicitações, reprocessando a solicitação %s.%n", agent.getName(), request.getId());
        }
    }

}
