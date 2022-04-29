package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.request.CurrentAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.request.SpecialAccountRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpecialAccountServiceTest {

    @Autowired
    SpecialAccountService specialAccountService;

    @Autowired
    SpecialAccountRepository specialAccountRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;



    @Test
    @Order(6)
    void findSpecialAccountById() {

        SpecialAccount account = specialAccountService.findById(5);

        assertEquals(account.getBalance(), 199.88);
    }

    @Test
    @Order(5)
    void findSpecialAccountByIdNull() {
        assertNull(specialAccountService.findById(9999999));
    }

    @Test
    @Order(6)
    void findAllSpecialAccount() {
        List<SpecialAccount> findAllAccountsInDb = specialAccountService.findAll();
        assertTrue(findAllAccountsInDb.size() > 0);
    }

    @Test
    @Order(2)
    void updateSpecialAccount() throws BusinessException {
        Customer customer = new Customer();
        customer.setSocialName("Nome");
        customer.setDocumentNumber("teste");
        customer.setAddress("Endereço");
        customer.setCustomerType(CustomerType.INDIVIDUAL);
        customerService.createCustomer(customer);

        SpecialAccountRequest newCurrentAccount = new SpecialAccountRequest();
        newCurrentAccount.setAccountNumber("1234567");
        newCurrentAccount.setBalance(199.88);
        newCurrentAccount.setDocumentNumber("teste");
        specialAccountService.updateSpecialAccount(newCurrentAccount);

        assertEquals(newCurrentAccount.getBalance(),199.88);

    }

    @Test
    @Order(3)
    void updateCurrentAccountNonexistent() {

        SpecialAccountRequest newCurrentAccount = new SpecialAccountRequest();
        newCurrentAccount.setIdAccount(1);
        newCurrentAccount.setAccountNumber("1234568888");
        newCurrentAccount.setBalance(199.88);
        newCurrentAccount.setDocumentNumber("teste");


        assertThrows(BusinessException.class, () -> {specialAccountService.updateSpecialAccount(newCurrentAccount);});

    }

    @Test
    @Order(1)
    void createSpecialAccount() throws BusinessException {
        Customer customer = new Customer();
        customer.setSocialName("Nome");
        customer.setDocumentNumber("2222");
        customer.setAddress("Endereço");
        customer.setCustomerType(CustomerType.INDIVIDUAL);
        customerService.createCustomer(customer);

        SpecialAccountRequest newSpecialAccount = new SpecialAccountRequest();
        newSpecialAccount.setAccountNumber("1234567");
        newSpecialAccount.setBalance(200.00);
        newSpecialAccount.setDocumentNumber("2222");
        newSpecialAccount.setLimitAmount(2000.00);
        specialAccountService.create(newSpecialAccount);

        SpecialAccount accountExist = specialAccountService.findById(5);
        assertEquals(accountExist.getLimitAmount(), 2000.00);
    }

    @Test
    @Order(7)
    void createSpecialAccountDuplicateException() {

        SpecialAccountRequest newCurrentAccount = new SpecialAccountRequest();
        newCurrentAccount.setAccountNumber("1234567");
        newCurrentAccount.setBalance(199.88);
        newCurrentAccount.setDocumentNumber("teste");

        assertThrows(BusinessException.class, () -> {
            specialAccountService.updateSpecialAccount(newCurrentAccount);
        });
    }

    @Test
    @Order(8)
    void createSpecialAccountWithoutCustomerException()  {
        Customer nonexistent = new Customer();
        nonexistent.setDocumentNumber("3");

        SpecialAccountRequest newSpecialAccount = new SpecialAccountRequest();
        newSpecialAccount.setAccountNumber("1234567");
        newSpecialAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
        newSpecialAccount.setBalance(200.00);
        newSpecialAccount.setCustomer(nonexistent);

        assertThrows(BusinessException.class, () -> {
            specialAccountService.create(newSpecialAccount);
        });
    }

    @Test
    @Order(9)
    void delete() throws BusinessException {

        specialAccountService.delete(specialAccountRepository.findByAccountNumber("1234567").get().getIdAccount());
        assertTrue((specialAccountRepository.findByAccountNumber("1234567").isEmpty()));
    }

    @Test
    @Order(10)
    void  deleteNonexistentSpecialAccount(){
        assertThrows(BusinessException.class,() -> {
            specialAccountService.delete(44);
        });
    }

}