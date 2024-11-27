package br.com.flowPay;

import br.com.flowPay.enums.agent.AgentStatus;
import br.com.flowPay.enums.request.RequestType;
import br.com.flowPay.model.supportAgent.SupportAgent;
import br.com.flowPay.repository.SupportAgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FlowPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowPayApplication.class, args);
	}


	@Bean
	public CommandLineRunner initializeAgents(SupportAgentRepository repository) {
		return args -> {
			repository.save(new SupportAgent("Agent A", RequestType.LOAN_CONTRACT, AgentStatus.ONLINE));
			repository.save(new SupportAgent("Agent B", RequestType.LOAN_CONTRACT, AgentStatus.ONLINE));

			repository.save(new SupportAgent("Agent C", RequestType.CARD_ISSUES, AgentStatus.ONLINE));
			repository.save(new SupportAgent("Agent D", RequestType.CARD_ISSUES, AgentStatus.ONLINE));

			repository.save(new SupportAgent("Agent E", RequestType.OTHER_MATTERS, AgentStatus.ONLINE));
			repository.save(new SupportAgent("Agent F", RequestType.OTHER_MATTERS, AgentStatus.ONLINE));

			System.out.println("Agentes criados com sucesso!");
		};
	}
}
