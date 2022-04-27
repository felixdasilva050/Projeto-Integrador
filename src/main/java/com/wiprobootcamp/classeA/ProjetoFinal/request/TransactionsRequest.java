package com.wiprobootcamp.classeA.ProjetoFinal.request;

public class TransactionsRequest {
    private String accountNumber;
    private String transactionDate;
    private Double value;

    public TransactionsRequest() {
    }

    public TransactionsRequest(String accountNumber, String transactionDate, Double value) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.value = value;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TransactionsRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", transactionDate=" + transactionDate +
                ", value=" + value +
                '}';
    }
}
