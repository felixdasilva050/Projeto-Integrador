package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.TransactionsRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.service.CurrentAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currentAccount")
public class CurrentAccountController {

    @Autowired
    private CurrentAccountService currentAccountService;

    @GetMapping("/{idAccount}")
    public ResponseEntity<CurrentAccount> getCurrentAccountById(@PathVariable Integer idAccount) {
        CurrentAccount currentAccount = this.currentAccountService.findById(idAccount);
        return ResponseEntity.ok().body(currentAccount);
    }

    @GetMapping("/getAll")
    public Iterable<CurrentAccount> getAllCurrentAccounts() {
        return this.currentAccountService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCurrentAccount(@RequestBody CurrentAccount currentAccount) {
        try {
            currentAccountService.create(currentAccount);
            return ResponseEntity.status(HttpStatus.CREATED).body("Conta corrente " + currentAccount.getAccountNumber() + " criada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCurrentAccount(@RequestBody CurrentAccount currentAccount) {
        try {
            currentAccountService.update(currentAccount);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Conta corrente " + currentAccount.getAccountNumber() + " atualizada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCurrentAccount(@PathVariable Integer id){
        try {
            currentAccountService.delete(id);
            return ResponseEntity.status(HttpStatus.GONE).body("Conta deletada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta corrente não encontrada no banco de dados!");
        }
    }
}