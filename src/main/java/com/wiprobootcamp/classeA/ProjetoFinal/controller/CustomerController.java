package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")

public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findById/{idCustomer}")
    public ResponseEntity<Customer> getIndividualById(@PathVariable Integer idCustomer) throws BusinessException {
        Customer individual = this.customerService.findCustomerById(idCustomer);
        return ResponseEntity.ok().body(individual);
    }

    @GetMapping("/findall")
    public Iterable<Customer> getAllCustomers() {
            return this.customerService.findAllCustomer();
        }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) throws BusinessException {
    return customerService.createCustomer(customer);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws Exception {
        customerService.updateCustomer(customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
    }

    @DeleteMapping("/delete/{idCustomer}")
    public ResponseEntity<String> deleteCurrentAccount(@PathVariable Integer idCustomer){
        try {
            customerService.deleteCustomerBy(idCustomer);
            return ResponseEntity.status(HttpStatus.GONE).body("Conta deletada com sucesso!");
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta corrente não encontrada no banco de dados!");
        }
    }
}
