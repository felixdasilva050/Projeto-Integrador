package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.request.CurrentAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.service.CurrentAccountService;
import com.wiprobootcamp.classeA.ProjetoFinal.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currentAccount")
public class CurrentAccountController {

    private final CurrentAccountService currentAccountService;

    public CurrentAccountController(CurrentAccountService currentAccountService, ReceiptService receiptService) {
        this.currentAccountService = currentAccountService;
    }

    @GetMapping("/findById/{idAccount}")
    public ResponseEntity<CurrentAccount> getCurrentAccountById(@PathVariable Integer idAccount) {
        return ResponseEntity.ok().body(currentAccountService.findCurrentAccountById(idAccount));
    }


    @GetMapping("/getAll")
    public ResponseEntity<Iterable<CurrentAccount>> getAllCurrentAccounts() {
        return ResponseEntity.ok().body(currentAccountService.findAllCurrentAccount());
    }


    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CurrentAccount> createCurrentAccount(@RequestBody CurrentAccountRequest currentAccountRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(currentAccountService.createCurrentAccount(currentAccountRequest));
    }


    @PutMapping("/update")
    public ResponseEntity<CurrentAccount> updateIndividual(@RequestBody CurrentAccount currentAccount) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(currentAccountService.updateCurrentAccount(currentAccount));
    }


    @DeleteMapping("/delete/{idDelete}")
    public ResponseEntity<String> deleteCurrentAccount(@PathVariable Integer idDelete){
        try {
            currentAccountService.delete(idDelete);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Conta deletada com sucesso!");
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta corrente não encontrada no banco de dados!");
        }
    }


    @PutMapping("/currentWithdraw")
    public ResponseEntity<String> currentWithdraw(@RequestBody TransactionsRequest transactionsRequest) {
        try {
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(currentAccountService.withdrawCash(transactionsRequest));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }


    @PutMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestBody TransactionsRequest transactionsRequest) {
        try {
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(currentAccountService.depositMoney(transactionsRequest));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}