package com.wiprobootcamp.classeA.ProjetoFinal.model;

import java.util.Date;

public class TransactionsRequest {
    private String accountNumber;
    private String transactionDate;
    private Double debitValue;

    public TransactionsRequest() {
    }

    public TransactionsRequest(String accountNumber, String transactionDate, Double debitValue) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.debitValue = debitValue;
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

    public Double getDebitValue() {
        return debitValue;
    }

    public void setDebitValue(Double debitValue) {
        this.debitValue = debitValue;
    }

    @Override
    public String toString() {
        return "TransactionsRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", transactionDate=" + transactionDate +
                ", debitValue=" + debitValue +
                '}';
    }
}
