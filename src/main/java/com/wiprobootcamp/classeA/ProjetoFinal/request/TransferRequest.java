package com.wiprobootcamp.classeA.ProjetoFinal.request;

public class TransferRequest {
    private String originAccountNumber;
    private String destinationAccountNumber;
    private Double TransferValue;
    private String tranferDate;

    public TransferRequest() {
    }

    public TransferRequest(String originAccountNumber, String destinationAccountNumber, Double transferValue, String tranferDate) {
        this.originAccountNumber = originAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        TransferValue = transferValue;
        this.tranferDate = tranferDate;
    }

    public String getOriginAccountNumber() {
        return originAccountNumber;
    }

    public void setOriginAccountNumber(String originAccountNumber) {
        this.originAccountNumber = originAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Double getTransferValue() {
        return TransferValue;
    }

    public void setTransferValue(Double transferValue) {
        TransferValue = transferValue;
    }

    public String getTranferDate() {
        return tranferDate;
    }

    public void setTranferDate(String tranferDate) {
        this.tranferDate = tranferDate;
    }
}
