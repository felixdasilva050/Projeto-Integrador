package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiprobootcamp.classeA.ProjetoFinal.model.LegalEntity;
import com.wiprobootcamp.classeA.ProjetoFinal.service.LegalService;

@RestController
@RequestMapping("/legal")
@CrossOrigin("*")
public class LegalController {

    @Autowired
    private LegalService service;

    @GetMapping("/{idCustomer}")
    public ResponseEntity<LegalEntity> getLegalById(@PathVariable Integer idCustomer) {
        LegalEntity obj = this.service.findLegalCustomerById(idCustomer);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Iterable<LegalEntity>> getAllLegalEntity() {
        Iterable<LegalEntity> allLegalEntityDb = this.service.findAllLegalCustomer();
        return ResponseEntity.ok().body(allLegalEntityDb);
    }

    @PostMapping("/create")
    public ResponseEntity<LegalEntity> createLegalEntity(@RequestBody LegalEntity legal) {
        LegalEntity newObj = this.service.createLegalCustomer(legal);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createLegalCustomer(legal));
    }

    @PutMapping("/update/{idCustomer}")
    public ResponseEntity<LegalEntity> updateLegalEntity(@PathVariable Integer idCustomer, @RequestBody LegalEntity obj) {
        LegalEntity uplegal = this.service.updateLegalCustomer(idCustomer, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(uplegal);
    }

    @DeleteMapping("/delete/{idCustomer}")
    public ResponseEntity<Void> deleteLegalEntity(@PathVariable Integer idCustomer) {
        this.service.deleteLegalCustomer(idCustomer);
        return ResponseEntity.noContent().build();
    }
}
