package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
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
    public SpecialAccount getSpecialAccountById(@PathVariable Integer idAccount) throws BusinessException {
        return specialAccountService.findById(idAccount);
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
    public SpecialAccount updateSpecialAccount(@RequestBody SpecialAccountRequest specialAccountRequest) throws Exception {
        return specialAccountService.updateSpecialAccount(specialAccountRequest);
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



}