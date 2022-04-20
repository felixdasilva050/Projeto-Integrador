package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CreditCard;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard createCreditCard(CreditCard creditCard){
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
}
