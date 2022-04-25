package com.wiprobootcamp.classeA.ProjetoFinal.model;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;

import javax.persistence.Entity;

@Entity
public class CurrentAccountRequest extends Account {

	private String documentNumber;


	public CurrentAccountRequest(String documentNumber) {
		this.documentNumber = documentNumber;

	}

	public CurrentAccountRequest() {

	}


	// Métodos Setter


	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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


}
