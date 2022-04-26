package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Receipt;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.ReceiptRepository;
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

    public String createReceipt(TransactionsRequest transactionsRequest) {
        Receipt newReceipt = new Receipt();
        String id = UUID.randomUUID().toString();
        newReceipt.setIdReceipt(id);
        newReceipt.setAccountNumber(transactionsRequest.getAccountNumber());
        newReceipt.setValue(transactionsRequest.getValue());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String todayFormated = sdf.format(new Date());
        newReceipt.setTransactionDate(todayFormated);

        receiptRepository.save(newReceipt);

        String title = "Comprovante de transação ";
        String idTransaction = "Id da transação número: " + newReceipt.getIdReceipt();
        String valueTransaction = "No valor de: " + newReceipt.getValue();
        String dateTransaction = "Transação realizada na data de :" + newReceipt.getTransactionDate();
        String dataReceipt = title + " " + idTransaction + " " + valueTransaction + " " + dateTransaction;

        return dataReceipt;

    }

    public List<Receipt> allReceipts() {
        return receiptRepository.findAll();
    }

}
