package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Individual;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class IndividualService {

    Logger logger = Logger.getLogger(IndividualService.class.getName());

    @Autowired
    private IndividualRepository individualRepository;

    public void createIndividualCustomer(Individual individual) throws Exception {
        //Verifica se já existe um CPF no banco de dados.
        Optional<Individual> findCpf = individualRepository.findByCpf(individual.getCpf());

        //Retornando um cliente já cadastrado jogamos uma exception.
        if(findCpf.isPresent()){
            logger.info("Cliente já existe no banco de dados.");
            throw new Exception("Cliente já cadastrado");
        }
        Individual newIndividual = new Individual();
        newIndividual.setCustomerName(individual.getCustomerName());
        newIndividual.setAddress(individual.getAddress());
        newIndividual.setCpf(individual.getCpf());
        newIndividual.setCostumerType(CustomerType.INDIVIDUAL);

        individualRepository.save(newIndividual);
    }

    public Individual findIndividualCustomerById(Integer idCustomer) {
        Optional<Individual> findIndCustRepo = individualRepository.findById(idCustomer);
        return findIndCustRepo.orElse(null);
    }

    public Individual updateIndividualCustomer(Individual individual) throws Exception {
        Optional<Individual> findCustomer = individualRepository.findByCpf(individual.getCpf());
        if(findCustomer.isEmpty()) {
            logger.info("Cliente não encontrado no banco de dados");
            throw new Exception("Cliente não existe!");
        }
        Individual upIndidual = findIndividualCustomerById(findCustomer.get().getIdCustomer());
        upIndidual.setCustomerName(individual.getCustomerName());
        upIndidual.setCpf(individual.getCpf());
        upIndidual.setAddress(individual.getAddress());
        upIndidual.setCostumerType(CustomerType.INDIVIDUAL);
        return individualRepository.save(upIndidual);
    }

    public Iterable<Individual> findAllIndividualCustomer() {
        return individualRepository.findAll();
    }

    public void deleteIndividualCustomer(Integer idCustomer) {
        findIndividualCustomerById(idCustomer);
        individualRepository.deleteById(idCustomer);
    }

}
