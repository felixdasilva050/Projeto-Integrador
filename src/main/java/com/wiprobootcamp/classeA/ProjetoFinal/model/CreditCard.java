package com.wiprobootcamp.classeA.ProjetoFinal.model;

public class CreditCard {
    private Integer idCreditCard;
    private String cardNumber;
    private Double cardLimit;

    public CreditCard() {
    }

    public Integer getIdCreditCard() {
        return idCreditCard;
    }

    public void setIdCreditCard(Integer idCreditCard) {
        this.idCreditCard = idCreditCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Double cardLimit) {
        this.cardLimit = cardLimit;
    }
}
