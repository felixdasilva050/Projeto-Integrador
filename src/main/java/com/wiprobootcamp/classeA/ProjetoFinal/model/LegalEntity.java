package com.wiprobootcamp.classeA.ProjetoFinal.model;

public class LegalEntity extends Customer {
    private Integer idLegalEntity;
    private String cnpj;
    private String companyName;

    public LegalEntity() {
    }

    public LegalEntity(Integer idLegalEntity, String cnpj, String companyName) {
        this.idLegalEntity = idLegalEntity;
        this.cnpj = cnpj;
        this.companyName = companyName;
    }

    public Integer getIdLegalEntity() {
        return idLegalEntity;
    }

    public void setIdLegalEntity(Integer idLegalEntity) {
        this.idLegalEntity = idLegalEntity;
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
