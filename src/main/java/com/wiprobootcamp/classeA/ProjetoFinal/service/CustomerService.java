package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomerService {

    Logger logger = Logger.getLogger(CustomerService.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) throws Exception {
        //Verifica se já existe um CPF no banco de dados.
        Optional<Customer> findCustomer = customerRepository.findByDocumentNumber(customer.getDocumentNumber());

        //Retornando um cliente já cadastrado jogamos uma exception.
        if(findCustomer.isPresent()){
            logger.info("Cliente já existe no banco de dados.");
            throw new Exception("Cliente já cadastrado");
        }
        Customer newCustomer = new Customer();
        newCustomer.setSocialName(customer.getSocialName());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setDocumentNumber(customer.getDocumentNumber());
        newCustomer.setCustomerType(customer.getCustomerType());

       return customerRepository.save(newCustomer);
    }

//    public Individual findIndividualCustomerById(Integer idCustomer) {
//        Optional<Individual> findIndCustRepo = individualRepository.findById(idCustomer);
//        return findIndCustRepo.orElse(null);
//    }

    public Customer updateCustomer(Customer customer) throws Exception {
        Optional<Customer> findCustomer = customerRepository.findByDocumentNumber(customer.getDocumentNumber());
        if(findCustomer.isEmpty()) {
            logger.info("Cliente não encontrado no banco de dados");
            throw new Exception("Cliente não existe!");
        }
        Customer upCustomer = new Customer();
        upCustomer.setIdCustomer(customer.getIdCustomer());
        upCustomer.setSocialName(customer.getSocialName());
        upCustomer.setDocumentNumber(customer.getDocumentNumber());
        upCustomer.setAddress(customer.getAddress());
        upCustomer.setCustomerType(customer.getCustomerType());
        return customerRepository.save(upCustomer);

    }

    public Iterable<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

//    public void deleteIndividualCustomer(Integer idCustomer) {
//        findIndividualCustomerById(idCustomer);
//        individualRepository.deleteById(idCustomer);
//    }

}
