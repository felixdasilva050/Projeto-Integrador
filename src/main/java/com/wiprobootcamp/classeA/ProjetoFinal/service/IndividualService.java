package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Individual;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndividualService {
    @Autowired
    private IndividualRepository individualRepository;

    public Individual createIndividualCustomer(Individual obj) {
        return individualRepository.save(obj);
    }

    public Individual findIndividualCustomerById(Integer idCustomer) {
        Optional<Individual> findIndCustRepo = individualRepository.findById(idCustomer);
        return findIndCustRepo.orElse(null);
    }

    public Individual updateIndividualCustomer(Integer idCustomer, Individual obj) {
        Individual upIndividual = findIndividualCustomerById(idCustomer);
        upIndividual.setCustomerName(obj.getCustomerName());
        upIndividual.setCpf(obj.getCpf());
        upIndividual.setAddress(obj.getAddress());
        upIndividual.setCostumerType(CustomerType.INDIVIDUAL);
        return individualRepository.save(upIndividual);
    }

    public Iterable<Individual> findAllIndividualCustomer() {
        return individualRepository.findAll();
    }

    public void deleteIndividualCustomer(Integer idCustomer) {
        findIndividualCustomerById(idCustomer);
        individualRepository.deleteById(idCustomer);
    }

}
