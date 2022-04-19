package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Individual;
import com.wiprobootcamp.classeA.ProjetoFinal.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/individual")
@CrossOrigin("*")
public class IndividualController {

    @Autowired
    private IndividualService service;

    @GetMapping("/{idCustomer}")
    public ResponseEntity<Individual> getIndividualById(@PathVariable Integer idCustomer) {
        Individual obj = this.service.findIndividualCustomerById(idCustomer);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Iterable<Individual>> getAllIndividual() {
        Iterable<Individual> allIndividualDb = this.service.findAllIndividualCustomer();
        return ResponseEntity.ok().body(allIndividualDb);
    }

    @PostMapping("/create")
    public ResponseEntity<Individual> createIndividual(@RequestBody Individual individual) {
        Individual newObj = this.service.createIndividualCustomer(individual);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createIndividualCustomer(individual));
    }

    @PutMapping("/update/{idCustomer}")
    public ResponseEntity<Individual> updateIndividual(@PathVariable Integer idCustomer, @RequestBody Individual obj) {
        Individual upIndiv = this.service.updateIndividualCustomer(idCustomer, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(upIndiv);
    }

    @DeleteMapping("/delete/{idCustomer}")
    public ResponseEntity<Void> deleteIndividual(@PathVariable Integer idCustomer) {
        this.service.deleteIndividualCustomer(idCustomer);
        return ResponseEntity.noContent().build();
    }
}
