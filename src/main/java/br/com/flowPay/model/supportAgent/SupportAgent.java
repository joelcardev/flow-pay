package br.com.flowPay.model.supportAgent;

import br.com.flowPay.enums.agent.AgentStatus;
import br.com.flowPay.enums.request.RequestStatus;
import br.com.flowPay.enums.request.RequestType;
import br.com.flowPay.model.customer.CustomerRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners( AuditingEntityListener.class )
public class SupportAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    private AgentStatus status;

    @OneToMany(mappedBy = "supportAgent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerRequest> requests = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public SupportAgent(String name, RequestType requestType, AgentStatus status) {
        this.name = name;
        this.requestType = requestType;
        this.status = status;
    }

}

