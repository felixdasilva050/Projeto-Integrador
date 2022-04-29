package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Receipt;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransferRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;


    @PutMapping("/deposit")
    public Receipt depositMoney(@RequestBody TransactionsRequest transactionsRequest) throws BusinessException {
        return transactionsService.depositMoney(transactionsRequest);
    }

    @PutMapping("/withdraw")
    public Receipt withdrawMoney(@RequestBody TransactionsRequest transactionsRequest) throws BusinessException {
        return transactionsService.withdraw(transactionsRequest);
    }

    @PutMapping("/transfer")
    public String transferMoney(@RequestBody TransferRequest transferRequest) throws BusinessException {
        return transactionsService.transferMoney(transferRequest);
    }



}
