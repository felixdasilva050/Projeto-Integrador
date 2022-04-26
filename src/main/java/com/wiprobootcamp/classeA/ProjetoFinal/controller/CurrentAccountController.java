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
    private final ReceiptService receiptService;

    public CurrentAccountController(CurrentAccountService currentAccountService, ReceiptService receiptService) {
        this.currentAccountService = currentAccountService;
        this.receiptService = receiptService;
    }

    @GetMapping("/findById/{idAccount}")
    public ResponseEntity<CurrentAccount> getCurrentAccountById(@PathVariable Integer idAccount) {
        CurrentAccount currentAccount = this.currentAccountService.findCurrentAccountById(idAccount);
        return ResponseEntity.ok().body(currentAccount);
    }

    @GetMapping("/getAll")
    public Iterable<CurrentAccount> getAllCurrentAccounts() {
        return this.currentAccountService.findAllCurrentAccount();
    }

    @PostMapping("/create")
    @ResponseBody
    public CurrentAccount createCurrentAccount(@RequestBody CurrentAccountRequest currentAccountRequest) throws Exception {
        return  currentAccountService.createCurrentAccount(currentAccountRequest);
    }
    @PutMapping("/update")
    public ResponseEntity<CurrentAccount> updateIndividual(@RequestBody CurrentAccount currentAccount) throws Exception {
        currentAccountService.updateCurrentAccount(currentAccount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(currentAccount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCurrentAccount(@PathVariable Integer id){
        try {
            currentAccountService.delete(id);
            return ResponseEntity.status(HttpStatus.GONE).body("Conta deletada com sucesso!");
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta corrente não encontrada no banco de dados!");
        }
    }

    @PutMapping("/currentWithdraw")
    public ResponseEntity<String> currentWithdraw(@RequestBody TransactionsRequest transactionsRequest) {
        try {
           return ResponseEntity.status(HttpStatus.GONE).body(currentAccountService.withdrawCash(transactionsRequest));
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