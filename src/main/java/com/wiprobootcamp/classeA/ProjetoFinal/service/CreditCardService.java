package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CreditCard;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard createCreditCard(CreditCard creditCard){
        creditCard.setCardNumber(cardRandom());
        return creditCardRepository.save(creditCard);
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
}
