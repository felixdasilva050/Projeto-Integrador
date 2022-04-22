package com.wiprobootcamp.classeA.ProjetoFinal.model;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idCustomer;
    private String address;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer customerId) {
        this.idCustomer = customerId;
    }

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
