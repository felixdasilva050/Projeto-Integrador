package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.request.SpecialAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.service.SpecialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialAccount")
public class SpecialAccountController {

    @Autowired
    private SpecialAccountService specialAccountService;

    @GetMapping("/findById/{idAccount}")
    public ResponseEntity<SpecialAccount> getSpecialAccountById(@PathVariable Integer idAccount) {
        return ResponseEntity.ok().body(specialAccountService.findById(idAccount));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<SpecialAccount>> getAllSpecialAccounts() {
        return ResponseEntity.ok().body(specialAccountService.findAll());
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<SpecialAccount> createSpecialAccount(@RequestBody SpecialAccountRequest specialAccountRequest) throws Exception {
        return  ResponseEntity.status(HttpStatus.CREATED).body(specialAccountService.create(specialAccountRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<SpecialAccount> updateIndividual(@RequestBody SpecialAccount specialAccount) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(specialAccountService.updateSpecialAccount(specialAccount));
    }

    @DeleteMapping("/delete/{idSpecialAccount}")
    public ResponseEntity<String> deleteSpecialAccount(@PathVariable Integer idSpecialAccount) {
        try {
            specialAccountService.delete(idSpecialAccount);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Conta especial deletada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/specialWithdraw")
    public ResponseEntity<String> specialWithdraw(@RequestBody TransactionsRequest transactionsRequest) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(specialAccountService.specialWithdraw(transactionsRequest));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestBody TransactionsRequest transactionsRequest) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(specialAccountService.depositMoney(transactionsRequest));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}