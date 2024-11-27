package br.com.flowPay.services.agent;

import br.com.flowPay.model.customer.CustomerRequest;
import br.com.flowPay.model.supportAgent.SupportAgent;
import br.com.flowPay.repository.CustomerRequestRepository;
import br.com.flowPay.repository.SupportAgentRepository;
import br.com.flowPay.enums.request.RequestStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class SupportAgentService {

    private final CustomerRequestRepository requestRepository;
    private final SupportAgentRepository agentRepository;

    @Autowired
    public SupportAgentService(CustomerRequestRepository requestRepository, SupportAgentRepository agentRepository) {
        this.requestRepository = requestRepository;
        this.agentRepository = agentRepository;
    }

    /**
     * Método para finalizar uma solicitação.
     * @param requestId ID da solicitação a ser finalizada.
     * @return A solicitação atualizada.
     */
    @Transactional
    public Boolean finalizeRequest(UUID requestId) {

        CustomerRequest request = requestRepository.findById(requestId).orElse(null);

        if (request == null) {
            throw new IllegalArgumentException("Solicitação não encontrada!");
        }

        request.setStatus(RequestStatus.COMPLETED);
        requestRepository.save(request);

        CustomerRequest newRequest = requestRepository.findFirstByRequestTypeAndStatusOrderByCreatedAt(
                request.getRequestType(),
                RequestStatus.PENDING
        );
        if(Objects.nonNull(newRequest)){
            var agent = agentRepository.findById(request.getSupportAgent().getId());

            if (agent.isPresent()) {
                newRequest.setStatus(RequestStatus.IN_PROGRESS);
                newRequest.setSupportAgent(agent.get());
                requestRepository.save(newRequest);
            }
        }
        return true;
    }


}

