package br.com.flowPay.enums.request;

public enum RequestType {
    CARD_ISSUES("Problemas com cartão"),
    LOAN_CONTRACT("Contratação de empréstimo"),
    OTHER_MATTERS("Outros Assuntos");

    private final String description;

    RequestType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}