package br.com.flowPay.services.distribution;

import br.com.flowPay.dto.customer.CustomerRequestDTO;
import br.com.flowPay.enums.agent.AgentStatus;
import br.com.flowPay.enums.request.RequestStatus;
import br.com.flowPay.enums.request.RequestType;
import br.com.flowPay.model.customer.CustomerRequest;
import br.com.flowPay.model.supportAgent.SupportAgent;
import br.com.flowPay.repository.CustomerRequestRepository;
import br.com.flowPay.repository.SupportAgentRepository;
import br.com.flowPay.services.notification.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DistributionService {

    private final CustomerRequestRepository requestRepository;
    private final SupportAgentRepository agentRepository;
    private final NotificationService notificationService;

    public DistributionService(CustomerRequestRepository requestRepository, SupportAgentRepository agentRepository, NotificationService notificationService) {
        this.requestRepository = requestRepository;
        this.agentRepository = agentRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public UUID distributeRequest(CustomerRequestDTO requestDTO) {
        CustomerRequest request = requestDTO.toEntity();
        request.setStatus(RequestStatus.PENDING);
        requestRepository.save(request);
        notificationService.queueRequest(request, 0);
        return request.getId();
    }

    public SupportAgent findAvailableAgent(RequestType requestType) {
        int MAX_SIMULTANEOUS_REQUESTS = 3;
        return agentRepository.findFirstAvailableAgent(
                AgentStatus.ONLINE.name(),
                requestType.name(),
                MAX_SIMULTANEOUS_REQUESTS
        );
    }
}