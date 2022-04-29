package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {

	//Definição dos atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Integer idAccount;
	protected String accountNumber;
	protected Double balance;

	@OneToOne
	private Customer customer;
	protected AccountType accountType;

	public Account() {

	}

	public Account(Integer idAccount, String accountNumber, Double balance, CreditCard creditCard,
				   AccountType accountType) {
		super();
		this.idAccount = idAccount;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getIdAccount() {
		return idAccount;
	}
	
	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Double getBalance() {
		return balance;
	}


	public AccountType getAccountType() {
		return accountType;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((idAccount == null) ? 0 : idAccount.hashCode());
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
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountType != other.accountType)
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (idAccount == null) {
			if (other.idAccount != null)
				return false;
		} else if (!idAccount.equals(other.idAccount))
			return false;
		return true;
	}
}
