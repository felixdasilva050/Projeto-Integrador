package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @GetMapping("/{idCustomer}")
//    public ResponseEntity<Individual> getIndividualById(@PathVariable Integer idCustomer) {
//        Individual individual = this.service.findIndividualCustomerById(idCustomer);
//        return ResponseEntity.ok().body(individual);
//    }

    @GetMapping("/findall")
    public Iterable<Customer> getAllCustomers() {
        try {
            return this.customerService.findAllCustomer();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.toString());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer newCustomer = customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.toString());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws Exception {
        customerService.updateCustomer(customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
    }

//    @DeleteMapping("/delete/{idCustomer}")
//    public ResponseEntity<Void> deleteIndividual(@PathVariable Integer idCustomer) {
//        this.service.deleteIndividualCustomer(idCustomer);
//        return ResponseEntity.noContent().build();
//    }
}
