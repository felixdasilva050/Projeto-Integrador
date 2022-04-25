package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.service.CurrentAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currentAccount")
public class CurrentAccountController {

    private final CurrentAccountService currentAccountService;

    public CurrentAccountController(CurrentAccountService currentAccountService) {
        this.currentAccountService = currentAccountService;
    }
//
//    @GetMapping("/{idAccount}")
//    public ResponseEntity<CurrentAccount> getCurrentAccountById(@PathVariable Integer idAccount) {
//        CurrentAccount currentAccount = this.currentAccountService.findById(idAccount);
//        return ResponseEntity.ok().body(currentAccount);
//    }
//
    @GetMapping("/getAll")
    public Iterable<CurrentAccount> getAllCurrentAccounts() {
        return this.currentAccountService.findAll();
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
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteCurrentAccount(@PathVariable Integer id){
//        try {
//            currentAccountService.delete(id);
//            return ResponseEntity.status(HttpStatus.GONE).body("Conta deletada com sucesso!");
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta corrente não encontrada no banco de dados!");
//        }
//    }
}