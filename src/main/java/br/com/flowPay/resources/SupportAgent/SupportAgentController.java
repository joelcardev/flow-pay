package br.com.flowPay.resources.SupportAgent;

import br.com.flowPay.dto.agent.AgentStatusDTO;
import br.com.flowPay.model.customer.CustomerRequest;
import br.com.flowPay.services.agent.AgentStatusService;
import br.com.flowPay.services.agent.SupportAgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/agents")
public class SupportAgentController {

    private final AgentStatusService agentStatusService;
    private final SupportAgentService supportAgentService;


    public SupportAgentController(AgentStatusService agentStatusService, SupportAgentService supportAgentService) {
        this.agentStatusService = agentStatusService;
        this.supportAgentService = supportAgentService;
    }

    @PutMapping("/finalize-request/{requestId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean finalizeRequest(@PathVariable UUID requestId) {
        return supportAgentService.finalizeRequest(requestId);
    }

    @PutMapping("/status")
    public ResponseEntity<String> updateStatus(@RequestBody AgentStatusDTO statusDTO) {
        agentStatusService.updateAgentStatus(statusDTO);
        return ResponseEntity.ok("Status atualizado com sucesso.");
    }

    @GetMapping("/{agentId}/online")
    public ResponseEntity<Boolean> isAgentOnline(@PathVariable Long agentId) {
        boolean isOnline = agentStatusService.isAgentOnline(agentId);
        return ResponseEntity.ok(isOnline);
    }
}

