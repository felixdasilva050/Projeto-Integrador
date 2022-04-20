package com.wiprobootcamp.classeA.ProjetoFinal.repository;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
