package br.com.flowPay.repository;

import br.com.flowPay.enums.request.RequestStatus;
import br.com.flowPay.enums.request.RequestType;
import br.com.flowPay.model.customer.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, UUID> {

    CustomerRequest findFirstByRequestTypeAndStatusOrderByCreatedAt(RequestType requestType, RequestStatus status);

}
