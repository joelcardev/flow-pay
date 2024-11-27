package br.com.flowPay.dto.notification;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage implements Serializable {

    private static final long serialVersionUID = 7613246285656462722L;

    private String type;
    private String agentId;
    private UUID requestId;
    private String message;
}
