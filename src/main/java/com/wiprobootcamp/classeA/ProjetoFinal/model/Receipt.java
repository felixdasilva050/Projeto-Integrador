package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Receipt {
    @Id
    private String idReceipt;
    private String accountNumber;
    private Double value;
    private String TransactionDate;

    public Receipt() {
    }

    public Receipt(String idReceipt, String accountNumber, Double Value, String transactionDate) {
        this.idReceipt = idReceipt;
        this.accountNumber = accountNumber;
        this.value = Value;
        TransactionDate = transactionDate;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }

    public String getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(String idReceipt) {
        this.idReceipt = idReceipt;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }



    public Double getValue() {
        return value;
    }

    public void setValue(Double debitValue) {
        this.value = debitValue;
    }
}
