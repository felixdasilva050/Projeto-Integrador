package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;

@Entity
public class CurrentAccount extends Account {
	
	@OneToOne
	@JoinColumn(name = "individual_id")
	private Individual individual;

	// Construtor padrão
	public CurrentAccount() {
	}
	
	public CurrentAccount(Individual individual) {
		super();
		this.individual = individual;
	}

	// Métodos Setter

	public Individual getIndividual() {
		return individual;
	}

	public void setIndividual(Individual individual) {
		this.individual = individual;
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

	//Equals e Hascode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((individual == null) ? 0 : individual.hashCode());
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
		CurrentAccount other = (CurrentAccount) obj;
		if (individual == null) {
			if (other.individual != null)
				return false;
		} else if (!individual.equals(other.individual))
			return false;
		return true;
	}
}
