package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.*;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;

//Representa uma entidade em nossa base de dados
@Entity
public class SpecialAccount extends Account {


	private Double limitAmount;



	// Construtor da Superclasse
	public SpecialAccount() {
		super();
	}

	// Construtor com os atributos
	public SpecialAccount(Double limitAmount) {
		super();
		this.limitAmount = limitAmount;
	}

	// Métodos getters e setters


	public Double getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(Double limitAmount) {
		this.limitAmount = limitAmount;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}



	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}


	//Métodos Equals e Hascode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((limitAmount == null) ? 0 : limitAmount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecialAccount other = (SpecialAccount) obj;
		if (limitAmount == null) {
			if (other.limitAmount != null)
				return false;
		} else if (!limitAmount.equals(other.limitAmount))
			return false;
		return true;
	}
}
