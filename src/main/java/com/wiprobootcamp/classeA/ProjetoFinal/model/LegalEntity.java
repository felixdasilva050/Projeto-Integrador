package com.wiprobootcamp.classeA.ProjetoFinal.model;

import javax.persistence.Entity;

@Entity
public class LegalEntity extends Customer {

	private String cnpj;
	private String companyName;

	public LegalEntity() {
	}

	public LegalEntity(Integer idLegalEntity, String cnpj, String companyName) {
		this.cnpj = cnpj;
		this.companyName = companyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
