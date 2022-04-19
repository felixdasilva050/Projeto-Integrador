package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;

@Entity
public class CurrentAccount extends Account {

	// O atributo abaixo representa o Id da classe
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurrentAccout;

	// Construtor padrão
	public CurrentAccount() {
	}

	// construtor com os atributos
	public CurrentAccount(Integer idCurrentAccout) {
		super();
		this.idCurrentAccout = idCurrentAccout;
	}
	
	//Métodos Getters e Setters
	public Integer getIdCurrentAccout() {
		return idCurrentAccout;
	}

	public void setIdCurrentAccout(Integer idCurrentAccout) {
		this.idCurrentAccout = idCurrentAccout;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	//Métodos Equals e Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCurrentAccout == null) ? 0 : idCurrentAccout.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentAccount other = (CurrentAccount) obj;
		if (idCurrentAccout == null) {
			if (other.idCurrentAccout != null)
				return false;
		} else if (!idCurrentAccout.equals(other.idCurrentAccout))
			return false;
		return true;
	}
}
