package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Individual;
import com.wiprobootcamp.classeA.ProjetoFinal.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/individual")
@CrossOrigin("*")
public class IndividualController {

    @Autowired
    private IndividualService service;

    @GetMapping("/{idCustomer}")
    public ResponseEntity<Individual> getIndividualById(@PathVariable Integer idCustomer) {
        Individual individual = this.service.findIndividualCustomerById(idCustomer);
        return ResponseEntity.ok().body(individual);
    }

    @GetMapping("/findall")
    public Iterable<Individual> getAllIndividual() {
        return this.service.findAllIndividualCustomer();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createIndividualCustomer(@RequestBody Individual individual) {
        try {
            service.createIndividualCustomer(individual);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente " + individual.getCustomerName() + " criado com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateIndividual(Individual individual) {
    try {
        service.updateIndividualCustomer(individual);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(individual.getCustomerName() + " Updated!");
    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    }

    @DeleteMapping("/delete/{idCustomer}")
    public ResponseEntity<Void> deleteIndividual(@PathVariable Integer idCustomer) {
        this.service.deleteIndividualCustomer(idCustomer);
        return ResponseEntity.noContent().build();
    }
}
