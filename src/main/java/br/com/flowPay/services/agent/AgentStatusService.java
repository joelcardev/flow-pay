package br.com.flowPay.services.agent;

import br.com.flowPay.dto.agent.AgentStatusDTO;
import br.com.flowPay.enums.agent.AgentStatus;
import br.com.flowPay.model.supportAgent.SupportAgent;
import br.com.flowPay.repository.SupportAgentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgentStatusService {
    private final SupportAgentRepository agentRepository;

    public AgentStatusService(SupportAgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Transactional
    public void updateAgentStatus(AgentStatusDTO statusDTO) {
        SupportAgent agent = agentRepository.findById(statusDTO.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("Agente não encontrado"));

        agent.setStatus(statusDTO.getStatus());
        agentRepository.save(agent);
    }

    public boolean isAgentOnline(Long agentId) {
        SupportAgent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("Agente não encontrado"));

        return agent.getStatus() == AgentStatus.ONLINE;
    }
}

