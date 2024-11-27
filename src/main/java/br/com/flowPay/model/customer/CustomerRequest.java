package br.com.flowPay.model.customer;


import br.com.flowPay.dto.customer.CustomerRequestDTO;
import br.com.flowPay.enums.request.RequestStatus;
import br.com.flowPay.enums.request.RequestType;
import br.com.flowPay.model.supportAgent.SupportAgent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners( AuditingEntityListener.class )
public class CustomerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support_agent_id")
    private SupportAgent supportAgent;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
