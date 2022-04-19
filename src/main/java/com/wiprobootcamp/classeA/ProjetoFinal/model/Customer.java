package com.wiprobootcamp.classeA.ProjetoFinal.model;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;

public abstract class Customer {
    private String address;
    private CustomerType customerType;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerType getCostumerType() {
        return customerType;
    }

    public void setCostumerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
