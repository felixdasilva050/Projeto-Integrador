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
    private CreditCardService creditCardService;


    @GetMapping("/findById/{idCreditCard}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Integer idCreditCard){
        return ResponseEntity.ok().body(creditCardService.findCreditCardById(idCreditCard));
    }


    @GetMapping("/getAll")
    public ResponseEntity<Iterable<CreditCard>> getCreditCard(){
        return ResponseEntity.ok().body(creditCardService.findAllCreditCard());
    }


    @PostMapping("/create")
    public CreditCard createCreditCard(@RequestBody CreditCardRequest creditCardRequest) throws BusinessException {
        return this.service.createCreditCard(creditCardRequest);
    }


    @PutMapping("/update/{idCreditCard}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable Integer idCreditCard, @RequestBody CreditCard creditCard){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(creditCardService.updatePassword(idCreditCard,creditCard));
    }


    @DeleteMapping("/delete/{idCreditCard}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable Integer idCreditCard){
        this.creditCardService.deleteCreditCard(idCreditCard);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/admin/{idCreditCard}")
    public ResponseEntity<CreditCard> updateLimitCreditCard(@PathVariable Integer idCreditCard, @RequestBody CreditCard creditCard){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(creditCardService.updateLimit(idCreditCard,creditCard));
    }
}
