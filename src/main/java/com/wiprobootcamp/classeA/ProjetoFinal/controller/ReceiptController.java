package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Receipt;
import com.wiprobootcamp.classeA.ProjetoFinal.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/allReceipts")
    public List<Receipt> getAllReceipts() {
        return this.receiptService.allReceipts();
    }
}
