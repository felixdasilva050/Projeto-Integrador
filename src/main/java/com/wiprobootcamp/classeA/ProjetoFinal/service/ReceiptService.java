package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Receipt;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.ReceiptRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public Receipt createReceipt(TransactionsRequest transactionsRequest) {
        Receipt newReceipt = new Receipt();
        String id = UUID.randomUUID().toString();
        newReceipt.setIdReceipt(id);
        newReceipt.setAccountNumber(transactionsRequest.getAccountNumber());
        newReceipt.setValue(transactionsRequest.getValue());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String todayFormated = sdf.format(new Date());
        newReceipt.setTransactionDate(todayFormated);

        receiptRepository.save(newReceipt);

        return newReceipt;

    }

    public List<Receipt> allReceipts() {
        return receiptRepository.findAll();
    }

    public Iterable<Receipt> receiptsOfAccount(String accountNumber){
        return receiptRepository.findAllByAccountNumber(accountNumber);
    }

}
