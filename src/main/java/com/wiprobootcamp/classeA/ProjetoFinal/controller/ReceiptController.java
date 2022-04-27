package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Receipt;
import com.wiprobootcamp.classeA.ProjetoFinal.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/getAllReceipts")
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return ResponseEntity.ok().body(receiptService.allReceipts());
    }


    @GetMapping("/getAllReceiptsByAccount")
    public ResponseEntity<Iterable<Receipt>> getAllByAccountNumber(String accountNumber) {
        return ResponseEntity.ok().body(receiptService.receiptsOfAccount(accountNumber));
    }
}
