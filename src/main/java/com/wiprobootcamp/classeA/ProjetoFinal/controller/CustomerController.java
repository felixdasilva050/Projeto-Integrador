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
        return ResponseEntity.ok().body(customerService.findCustomerById(idCustomer));
    }


    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
            return ResponseEntity.ok().body(customerService.findAllCustomer());
        }


    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
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
