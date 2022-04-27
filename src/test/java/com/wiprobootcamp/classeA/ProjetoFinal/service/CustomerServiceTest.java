package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeAll
    void setUp() throws BusinessException {

        Customer customer = new Customer();
        customer.setIdCustomer(1);
        customer.setSocialName("Nome");
        customer.setDocumentNumber("123456555");
        customer.setAddress("Endereço");
        customer.setCustomerType(CustomerType.INDIVIDUAL);
        customerService.createCustomer(customer);

        Customer customerToDelete = new Customer();
        customerToDelete.setIdCustomer(2);
        customerToDelete.setSocialName("Nome");
        customerToDelete.setDocumentNumber("123456");
        customerToDelete.setAddress("Endereço");
        customerToDelete.setCustomerType(CustomerType.INDIVIDUAL);
        customerService.createCustomer(customerToDelete);
    }

    @Test
    void createCustomer() {
     Optional<Customer> customerExist = customerRepository.findByDocumentNumber("123456555");
        assertEquals("Nome", customerExist.get().getSocialName());

    }

    @Test
    void createCustomerException() throws BusinessException {
        Customer customerDuplicate = new Customer();
        customerDuplicate.setSocialName("Nome");
        customerDuplicate.setDocumentNumber("123456555");
        customerDuplicate.setAddress("Endereço");
        customerDuplicate.setCustomerType(CustomerType.INDIVIDUAL);

        assertThrows(BusinessException.class, () -> {
            customerService.createCustomer(customerDuplicate);
        });
    }

    @Test
    void findCustomerIdEmpty() throws BusinessException {
        assertThrows(BusinessException.class, () -> {
            customerService.findCustomerById(10);
        });
    }

    @Test
    void findCustomerById() throws BusinessException {
    Customer findCustomer = customerService.findCustomerById(1);
        assertEquals(findCustomer.getAddress(), "Endereço");

    }

    @Test
    void updateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setIdCustomer(1);
        customer.setSocialName("Nome");
        customer.setDocumentNumber("123456555");
        customer.setAddress("Endereço");
        customer.setCustomerType(CustomerType.LEGAL_ENTITY);
        customerService.updateCustomer(customer);

        Customer findCustomer = customerService.findCustomerById(1);
        assertEquals(CustomerType.LEGAL_ENTITY, findCustomer.getCustomerType());
    }

    @Test
    void findAllCustomer() {
        List<Customer> findAll = (List<Customer>) customerService.findAllCustomer();
        assertTrue(findAll.size() > 0);

    }

    @Test
    void deleteCustomerBy() throws BusinessException {
        customerService.deleteCustomerBy(2);
        assertFalse(customerRepository.existsById(2));
    }
}