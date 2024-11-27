package br.com.flowPay.resources;

import br.com.flowPay.dto.customer.CustomerRequestDTO;
import br.com.flowPay.services.distribution.DistributionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/support-distribution")
public class SupportDistributionController {
    private final DistributionService distributionService;

    public SupportDistributionController(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    @PostMapping("/request")
    public ResponseEntity<String> distributeRequest(
            @Valid @RequestBody CustomerRequestDTO requestDTO
    ) {
        UUID requestId = distributionService.distributeRequest(requestDTO);
        return ResponseEntity.ok("Solicitação distribuída: " + requestId.toString());
    }
}
