package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Receipt;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionsService {

    @Autowired
    CurrentAccountRepository currentAccountRepository;

    @Autowired
    SpecialAccountRepository specialAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReceiptService receiptService;

    public Receipt withdraw(TransactionsRequest transactionsRequest) throws BusinessException {

        verifyWithdraw(transactionsRequest);

        return receiptService.createReceipt(transactionsRequest);
    }

    public Receipt depositMoney(TransactionsRequest transactionsRequest) throws BusinessException {
        Optional<SpecialAccount> verifySpecialAccountInDb = specialAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
        Optional<CurrentAccount> verifyCurrentAccountInDb = currentAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());

        if(verifySpecialAccountInDb.isPresent()) {

            Double defineBalance = verifySpecialAccountInDb.get().getBalance() + transactionsRequest.getValue();
            verifySpecialAccountInDb.get().setBalance(defineBalance);

            specialAccountRepository.save(verifySpecialAccountInDb.get());
            return receiptService.createReceipt(transactionsRequest);

        } else if(verifyCurrentAccountInDb.isPresent()) {
            Double defineBalance = verifyCurrentAccountInDb.get().getBalance() + transactionsRequest.getValue();
            verifyCurrentAccountInDb.get().setBalance(defineBalance);

            currentAccountRepository.save(verifyCurrentAccountInDb.get());
            return receiptService.createReceipt(transactionsRequest);

        } else {
                throw new BusinessException("Conta para depósito não localizada!");
            }
        }

    private void verifyWithdraw(TransactionsRequest transactionsRequest) throws BusinessException {
        Optional<SpecialAccount> verifySpecialAccountInDb = specialAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
        Optional<CurrentAccount> verifyCurrentAccountInDb = currentAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());

        //Verifica se existe uma conta especial;
        if (verifySpecialAccountInDb.isPresent()) {

            //Se sim, verifica se ela tem saldo, se sim realiza o saque normal sem debitar do limite
            if (verifySpecialAccountInDb.get().getBalance() >= transactionsRequest.getValue()) {
                verifySpecialAccountInDb.get().setBalance(verifySpecialAccountInDb.get().getBalance() - transactionsRequest.getValue());
                specialAccountRepository.save(verifySpecialAccountInDb.get());

            //Caso conta especial não tiver saldo o valor será debitado so limite
            } else if (verifySpecialAccountInDb.get().getBalance() < transactionsRequest.getValue() && (verifySpecialAccountInDb.get().getBalance() + verifySpecialAccountInDb.get().getLimitAmount()) > transactionsRequest.getValue()) {
                Double defineLimit = (verifySpecialAccountInDb.get().getBalance() - transactionsRequest.getValue()) + verifySpecialAccountInDb.get().getLimitAmount();
                verifySpecialAccountInDb.get().setLimitAmount(defineLimit);
                verifySpecialAccountInDb.get().setBalance(0.00);
                specialAccountRepository.save(verifySpecialAccountInDb.get());
            }

            //Verifica se existe conta corrente cadastrada
        } else if (verifyCurrentAccountInDb.isPresent()) {

            //Caso exista ela verifica se tem saldo e caso tenha irá realizar o saque;
            if (verifyCurrentAccountInDb.get().getBalance() >= transactionsRequest.getValue()) {
                verifyCurrentAccountInDb.get().setBalance(verifyCurrentAccountInDb.get().getBalance() - transactionsRequest.getValue());
                currentAccountRepository.save(verifyCurrentAccountInDb.get());

                //Caso a conta corrente não possua saldo retornamos a falta de saldo;
            } else {
                throw new BusinessException("Conta corrente não possui saldo suficiente!");
            }
            //Caso não localize a conta informada
        }else {
            throw new BusinessException("Conta para saque não existe");
        }
    }


    public String transferMoney(TransferRequest transferRequest) throws BusinessException {

        TransactionsRequest debitAccount = new TransactionsRequest();
        debitAccount.setAccountNumber(transferRequest.getOriginAccountNumber());
        debitAccount.setValue(transferRequest.getTransferValue());

        TransactionsRequest creditAccount = new TransactionsRequest();
        creditAccount.setAccountNumber(transferRequest.getDestinationAccountNumber());
        creditAccount.setValue(transferRequest.getTransferValue());

        withdraw(debitAccount);
        depositMoney(creditAccount);

        String receiveTransfer = "Transfrência no valor de: R$"+ transferRequest.getTransferValue() +" realizada com sucesso!";

        return receiveTransfer;
        }

}



