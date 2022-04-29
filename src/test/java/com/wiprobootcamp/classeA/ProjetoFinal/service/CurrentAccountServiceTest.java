package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Account;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.request.CurrentAccountRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CurrentAccountServiceTest {

    @Autowired
    CurrentAccountService currentAccountService;

    @Autowired
    CurrentAccountRepository currentAccountRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;



    @Test
    @Order(6)
    void findCurrentAccountById() {

        CurrentAccount account = currentAccountService.findCurrentAccountById(1);

        assertEquals(account.getBalance(), 199.88);
    }

    @Test
    @Order(5)
    void findCurrentAccountByIdNull() {
        assertNull(currentAccountService.findCurrentAccountById(9999999));
    }

    @Test
    @Order(6)
    void findAllCurrentAccount() {
        List<CurrentAccount> findAllAccountsInDb = currentAccountService.findAllCurrentAccount();
        assertTrue(findAllAccountsInDb.size() > 0);
    }

    @Test
    @Order(2)
    void updateCurrentAccount() throws BusinessException {
        Customer customer = new Customer();
        customer.setSocialName("Nome");
        customer.setDocumentNumber("teste");
        customer.setAddress("Endereço");
        customer.setCustomerType(CustomerType.INDIVIDUAL);
        customerService.createCustomer(customer);

        CurrentAccountRequest newCurrentAccount = new CurrentAccountRequest();
        newCurrentAccount.setIdAccount(1);
        newCurrentAccount.setAccountNumber("123456");
        newCurrentAccount.setBalance(199.88);
        newCurrentAccount.setDocumentNumber("teste");
        currentAccountService.updateCurrentAccount(newCurrentAccount);

        assertEquals(newCurrentAccount.getBalance(),199.88);

    }

    @Test
    @Order(3)
    void updateCurrentAccountNonexistent() {

        CurrentAccountRequest newCurrentAccount = new CurrentAccountRequest();
        newCurrentAccount.setAccountNumber("555");
        newCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
        newCurrentAccount.setBalance(199.88);
        newCurrentAccount.setDocumentNumber("nothing");

        assertThrows(BusinessException.class, () -> {currentAccountService.updateCurrentAccount(newCurrentAccount);});

    }

    @Test
    @Order(1)
    void createCurrentAccount() throws BusinessException {
        Customer customer = new Customer();
        customer.setSocialName("Nome");
        customer.setDocumentNumber("1111");
        customer.setAddress("Endereço");
        customer.setCustomerType(CustomerType.INDIVIDUAL);
        customerService.createCustomer(customer);

        CurrentAccountRequest newCurrentAccount = new CurrentAccountRequest();
        newCurrentAccount.setIdAccount(1);
        newCurrentAccount.setAccountNumber("123456");
        newCurrentAccount.setBalance(200.00);
        newCurrentAccount.setDocumentNumber("1111");
        currentAccountService.createCurrentAccount(newCurrentAccount);

        CurrentAccount accountExist = currentAccountService.findCurrentAccountById(1);
        assertEquals(accountExist.getBalance(), 200.00);
    }

    @Test
    @Order(7)
    void createCurrentAccountDuplicateException() {

        CurrentAccountRequest newCurrentAccount = new CurrentAccountRequest();
        newCurrentAccount.setIdAccount(1);
        newCurrentAccount.setAccountNumber("123456");
        newCurrentAccount.setBalance(200.00);
        newCurrentAccount.setDocumentNumber("1111");

        assertThrows(BusinessException.class, () -> {
            currentAccountService.createCurrentAccount(newCurrentAccount);
        });
    }

    @Test
    @Order(8)
    void createCurrentAccountWithoutCustomerException()  {
        Customer nonexistent = new Customer();
        nonexistent.setDocumentNumber("3");

        CurrentAccountRequest newCurrentAccount = new CurrentAccountRequest();
        newCurrentAccount.setAccountNumber("123456");
        newCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
        newCurrentAccount.setBalance(200.00);
        newCurrentAccount.setCustomer(nonexistent);

        assertThrows(BusinessException.class, () -> {
            currentAccountService.createCurrentAccount(newCurrentAccount);
        });
    }

    @Test
    @Order(9)
    void delete() throws BusinessException {

        currentAccountService.delete(currentAccountRepository.findByAccountNumber("123456").get().getIdAccount());
        assertTrue((currentAccountRepository.findByAccountNumber("123456").isEmpty()));
    }

    @Test
    @Order(10)
    void  deleteNonexistentCurrentAccount(){
        assertThrows(BusinessException.class,() -> {
            currentAccountService.delete(44);
        });
    }

}