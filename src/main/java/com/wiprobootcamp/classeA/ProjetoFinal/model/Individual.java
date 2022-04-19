package com.wiprobootcamp.classeA.ProjetoFinal.model;


import javax.persistence.Entity;

@Entity
public class Individual extends Customer {

    private String cpf;
    private String customerName;

    public Individual() {
    }

    public Individual(String cpf, String customerName) {
        this.cpf = cpf;
        this.customerName = customerName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
