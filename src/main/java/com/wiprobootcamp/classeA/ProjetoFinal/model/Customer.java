package com.wiprobootcamp.classeA.ProjetoFinal.model;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomer;
    private String documentNumber;
    private String socialName;
    private String address;
    private CustomerType customerType;


    public Customer() {
    }

    public Customer(Integer idCustomer, String documentNumber, String socialName, String address, CustomerType customerType) {
        this.idCustomer = idCustomer;
        this.documentNumber = documentNumber;
        this.socialName = socialName;
        this.address = address;
        this.customerType = customerType;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
