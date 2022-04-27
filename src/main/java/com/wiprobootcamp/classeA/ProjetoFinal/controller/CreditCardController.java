package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CreditCard;
import com.wiprobootcamp.classeA.ProjetoFinal.request.CreditCardRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditCard")
@CrossOrigin("*")
public class CreditCardController {

    @Autowired
    private CreditCardService service;

    @GetMapping("/{idCreditCard}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Integer idCreditCard){
        CreditCard creditCard = this.service.findCreditCardById(idCreditCard);
        return ResponseEntity.ok().body(creditCard);
    }

    @GetMapping
    public ResponseEntity<Iterable<CreditCard>> getCreditCard(){
        Iterable<CreditCard> allCreditCard = this.service.findAllCreditCard();
        return ResponseEntity.ok().body(allCreditCard);
    }

    @PostMapping("/create")
    public CreditCard createCreditCard(@RequestBody CreditCardRequest creditCardRequest) throws BusinessException {
        return this.service.createCreditCard(creditCardRequest);
    }

    @PutMapping("/update/{idCreditCard}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable Integer idCreditCard, @RequestBody CreditCard creditCard){
        CreditCard upCard = this.service.updatePassword(idCreditCard,creditCard);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(upCard);
    }

    @DeleteMapping("/delete/{idCreditCard}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Integer idCreditCard){
        this.service.deleteCreditCard(idCreditCard);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/admin/{idCreditCard}")
    public ResponseEntity<CreditCard> updateLimitCreditCard(@PathVariable Integer idCreditCard, @RequestBody CreditCard creditCard){
        CreditCard upCard = this.service.updateLimit(idCreditCard,creditCard);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(upCard);
    }
}
