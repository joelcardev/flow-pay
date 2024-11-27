package br.com.flowPay.dto.customer;

import br.com.flowPay.enums.request.RequestType;
import br.com.flowPay.model.customer.CustomerRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequestDTO {
    @NotNull
    private RequestType requestType;

    @NotBlank
    private String description;

    public CustomerRequest toEntity() {
        CustomerRequest request = new CustomerRequest();
        request.setRequestType(this.requestType);
        request.setDescription(this.description);
        return request;
    }

    public static CustomerRequestDTO fromEntity(CustomerRequest request) {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setRequestType(request.getRequestType());
        requestDTO.setDescription(request.getDescription());
        return requestDTO;
    }
}
