package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccountRequest;
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

//    @GetMapping("/{idAccount}")
//    public ResponseEntity<SpecialAccount> getSpecialAccountById(@PathVariable Integer idAccount) {
//        SpecialAccount specialAccount = this.specialAccountService.findById(idAccount);
//        return ResponseEntity.ok().body(specialAccount);
//    }

    @GetMapping("/getAll")
    public Iterable<SpecialAccount> getAllSpecialAccounts() {
        return this.specialAccountService.findAll();
    }

    @PostMapping("/create")
    @ResponseBody
    public SpecialAccount createSpecialAccount(@RequestBody SpecialAccountRequest specialAccountRequest) throws Exception {
        return  specialAccountService.create(specialAccountRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<SpecialAccount> updateIndividual(@RequestBody SpecialAccount specialAccount) throws Exception {
        specialAccountService.updateSpecialAccount(specialAccount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(specialAccount);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteSpecialAccount(@PathVariable Integer id) {
//        try {
//            specialAccountService.delete(id);
//         return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Conta especial deletada com sucesso!");
//        } catch (Exception ex) {
//         return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//        }
//    }

}