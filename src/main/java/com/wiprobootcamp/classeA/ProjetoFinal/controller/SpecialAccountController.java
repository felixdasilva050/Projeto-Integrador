package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.TransactionsRequest;
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

    @GetMapping("/{idAccount}")
    public ResponseEntity<SpecialAccount> getSpecialAccountById(@PathVariable Integer idAccount) {
        SpecialAccount specialAccount = this.specialAccountService.findById(idAccount);
        return ResponseEntity.ok().body(specialAccount);
    }

    @GetMapping("/getAll")
    public Iterable<SpecialAccount> getAllSpecialAccounts() {
        return this.specialAccountService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSpecialAccount(@RequestBody SpecialAccount specialAccount) {
        try {
            specialAccountService.create(specialAccount);
            return ResponseEntity.status(HttpStatus.CREATED).body("Conta especial " + specialAccount.getAccountNumber() + " criada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateSpecialAccount(SpecialAccount specialAccount) {
        try {
            specialAccountService.update(specialAccount);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Conta especial " + specialAccount.getAccountNumber() + " atualizada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/specialWithdraw")
    public ResponseEntity<String> specialWithdraw(@RequestBody TransactionsRequest transactionsRequest) throws Exception {
        try {
            specialAccountService.specialWithdraw(transactionsRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Saque de " + transactionsRequest.getDebitValue() + " Realizado com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }

    }

    @PutMapping("/deposit")
    public ResponseEntity<String> depositMoney(TransactionsRequest transactionsRequest) {
        try {
            specialAccountService.depositMoney(transactionsRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Depósito de " + transactionsRequest.getDebitValue() + " Realizado com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}