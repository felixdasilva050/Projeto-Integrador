package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CreditCard;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CreditCardRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.request.CreditCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    SpecialAccountRepository specialAccountRepository;

    public CreditCard createCreditCard(CreditCardRequest creditCardRequest) throws BusinessException {
        Optional<CurrentAccount> findCurrentAccountInDb = currentAccountRepository.findByAccountNumber(creditCardRequest.getAccountNumber());
        Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.findByAccountNumber(creditCardRequest.getAccountNumber());
        if(findCurrentAccountInDb.isPresent()){
            CreditCard newCreditCard = new CreditCard();
            newCreditCard.setCardNumber(cardRandom());
            newCreditCard.setAccount(findCurrentAccountInDb.get());
            newCreditCard.setCardLimit(creditCardRequest.getCardLimit());
            newCreditCard.setPassword(creditCardRequest.getPassword());
            return creditCardRepository.save(newCreditCard);

        }else if(findSpecialAccountInDb.isPresent()) {
            CreditCard newCreditCard = new CreditCard();
            newCreditCard.setCardNumber(cardRandom());
            newCreditCard.setAccount(findSpecialAccountInDb.get());
            newCreditCard.setCardLimit(creditCardRequest.getCardLimit());
            newCreditCard.setPassword(creditCardRequest.getPassword());
            return creditCardRepository.save(newCreditCard);
        } else
            throw new BusinessException("Conta não localizada!");
    }

    public CreditCard findCreditCardById(Integer idCreditCard){
        Optional<CreditCard> findIdCreditCard = creditCardRepository.findById(idCreditCard);
        return findIdCreditCard.orElse(null);
    }

    public CreditCard updatePassword(Integer idCreditCard, CreditCard creditCard){
        CreditCard findIdCreditCard = findCreditCardById(idCreditCard);
        findIdCreditCard.setPassword(creditCard.getPassword());
        return creditCardRepository.save(findIdCreditCard);
    }

    public Iterable<CreditCard> findAllCreditCard(){
        return creditCardRepository.findAll();
    }

    public void deleteCreditCard(Integer idCreditCard){
        findCreditCardById(idCreditCard);
        creditCardRepository.deleteById(idCreditCard);
    }

    public String cardRandom(){

        Random random = new Random();
        Integer numRandom;
        List<String> numeroCartao = new ArrayList<>();
        String mastercard = "5";
        mastercard = mastercard.concat(String.valueOf(random.nextInt(1000) + 100));
        numeroCartao.add(mastercard);
        for (int i = 0; i < 3; i++) {
            numRandom = random.nextInt(1000) + 1000;
            numeroCartao.add(String.valueOf(numRandom));
        }

        String numCard = String.join("-", numeroCartao);
        return numCard;
    }
    public CreditCard updateLimit(Integer idCreditCard, CreditCard creditCard){
        CreditCard findIdCreditCard = findCreditCardById(idCreditCard);
        findIdCreditCard.setCardLimit(creditCard.getCardLimit());
        return creditCardRepository.save(findIdCreditCard);
    }
}
