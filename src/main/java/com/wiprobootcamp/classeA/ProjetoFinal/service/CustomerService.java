package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
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

    public Customer createCustomer(Customer customer) throws BusinessException {
        Optional<Customer> findCustomer = customerRepository.findByDocumentNumber(customer.getDocumentNumber());
        if (findCustomer.isPresent()) {
            logger.info("Cliente já cadastrado no banco de dados");
            throw new BusinessException("Cliente já cadastrado!");
        }
        Customer newCustomer = new Customer();
        newCustomer.setSocialName(customer.getSocialName());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setDocumentNumber(customer.getDocumentNumber());
        newCustomer.setCustomerType(customer.getCustomerType());
        return customerRepository.save(newCustomer);
    }

    public Customer findCustomerById(Integer idCustomer) throws BusinessException {
        Optional<Customer> findCustomerId = customerRepository.findById(idCustomer);
        if(findCustomerId.isEmpty()) {
            throw new BusinessException("Id de cliente não localizada");
        }
        return findCustomerId.get();
    }

    public Customer updateCustomer(Customer customer) throws BusinessException {
        //Verifica se já existe um CPF no banco de dados.
        Optional<Customer> findCustomer = customerRepository.findByDocumentNumber(customer.getDocumentNumber());

        //Retornando um cliente já cadastrado jogamos uma exception.
        if (findCustomer.isEmpty()) {
            logger.info("Cliente não existe no banco de dados.");
            throw new BusinessException("Cliente não cadastrado");
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

    public void deleteCustomerBy(Integer idCustomer) throws BusinessException {
        findCustomerById(idCustomer);
        customerRepository.deleteById(idCustomer);
    }

}
