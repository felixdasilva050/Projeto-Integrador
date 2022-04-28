package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;

@Entity
public class CurrentAccount extends Account {

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}


}
