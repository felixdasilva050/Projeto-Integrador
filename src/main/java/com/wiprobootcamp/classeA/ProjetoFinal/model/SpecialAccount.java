package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;

//Representa uma entidade em nossa base de dados
@Entity
public class SpecialAccount extends Account {

	// representa nosso ID desta entidade em nossa base de dados
	@Id
	private Integer idSpecialAccount;

	// representa o valor do limite especial de uma conta especial
	private Double limitAmount;

	// Construtor da Superclasse
	public SpecialAccount() {
		super();
	}

	// Construtor com os atributos
	public SpecialAccount(Integer idSpecialAccount, Double limitAmount) {
		super();
		this.idSpecialAccount = idSpecialAccount;
		this.limitAmount = limitAmount;
	}

	// Métodos getters e setters
	public Integer getIdSpecialAccount() {
		return idSpecialAccount;
	}

	public void setIdSpecialAccount(Integer idSpecialAccount) {
		this.idSpecialAccount = idSpecialAccount;
	}

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

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// Método para relaizar saques especial quando o valor do saldo for menor..
	// ..que o valor a ser sacado
	public void specialWithdraw(Double money) {
		if (this.balance <= money) {
			balance = balance - money;
			limitAmount = balance + limitAmount;
			balance = 0.0;
		}
	}

	
	//Métodos Equals e Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSpecialAccount == null) ? 0 : idSpecialAccount.hashCode());
		result = prime * result + ((limitAmount == null) ? 0 : limitAmount.hashCode());
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
		SpecialAccount other = (SpecialAccount) obj;
		if (idSpecialAccount == null) {
			if (other.idSpecialAccount != null)
				return false;
		} else if (!idSpecialAccount.equals(other.idSpecialAccount))
			return false;
		if (limitAmount == null) {
			if (other.limitAmount != null)
				return false;
		} else if (!limitAmount.equals(other.limitAmount))
			return false;
		return true;
	}

}
