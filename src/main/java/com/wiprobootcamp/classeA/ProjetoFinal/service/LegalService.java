package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.LegalEntity;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.LegalRepository;

@Service
public class LegalService {
    @Autowired
    private LegalRepository legalRepository;

    public LegalEntity createLegalCustomer(LegalEntity obj) {
        return legalRepository.save(obj);
    }

    public LegalEntity findLegalCustomerById(Integer idCustomer) {
        Optional<LegalEntity> findIndCustRepo = legalRepository.findById(idCustomer);
        return findIndCustRepo.orElse(null);
    }

    public LegalEntity updateLegalCustomer(Integer idCustomer, LegalEntity obj) {
        LegalEntity upLegal = findLegalCustomerById(idCustomer);
        upLegal.setCnpj(obj.getCnpj());
        upLegal.setCompanyName(obj.getCompanyName());
        upLegal.setCostumerType(CustomerType.LEGAL_ENTITY);
        return legalRepository.save(upLegal);
    }

    public Iterable<LegalEntity> findAllLegalCustomer() {
        return legalRepository.findAll();
    }

    public void deleteLegalCustomer(Integer idCustomer) {
        findLegalCustomerById(idCustomer);
        legalRepository.deleteById(idCustomer);
    }

}
