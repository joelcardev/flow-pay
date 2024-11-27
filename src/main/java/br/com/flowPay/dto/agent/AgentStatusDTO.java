package br.com.flowPay.dto.agent;

import br.com.flowPay.enums.agent.AgentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgentStatusDTO {

    @NotNull
    private Long agentId;

    @NotNull
    private AgentStatus status;

}
