package com.wiprobootcamp.classeA.ProjetoFinal.request;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Account;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CreditCard;

import javax.persistence.Entity;

//Representa uma entidade em nossa base de dados
@Entity
public class SpecialAccountRequest extends Account {


	private Double limitAmount;
	private String documentNumber;



	// Construtor da Superclasse
	public SpecialAccountRequest() {
		super();
	}

	// Construtor com os atributos


	public SpecialAccountRequest(Double limitAmount, String documentNumber) {
		this.limitAmount = limitAmount;
		this.documentNumber = documentNumber;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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
		SpecialAccountRequest other = (SpecialAccountRequest) obj;
		if (limitAmount == null) {
			if (other.limitAmount != null)
				return false;
		} else if (!limitAmount.equals(other.limitAmount))
			return false;
		return true;
	}
}
