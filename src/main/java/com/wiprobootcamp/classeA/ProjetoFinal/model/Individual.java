package com.wiprobootcamp.classeA.ProjetoFinal.model;

public class Individual extends Customer{
    private Integer idCustomerIndiv;
    private String cpf;
    private String customerName;

    public Individual() {
    }

    public Individual(Integer idCustomerIndiv, String cpf, String customerName) {
        this.idCustomerIndiv = idCustomerIndiv;
        this.cpf = cpf;
        this.customerName = customerName;
    }

    public Integer getIdCustomerIndiv() {
        return idCustomerIndiv;
    }

    public void setIdCustomerIndiv(Integer idCustomerIndiv) {
        this.idCustomerIndiv = idCustomerIndiv;
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
