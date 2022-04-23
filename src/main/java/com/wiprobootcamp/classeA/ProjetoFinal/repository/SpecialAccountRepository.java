package com.wiprobootcamp.classeA.ProjetoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;

import java.util.Optional;

@Repository
public interface SpecialAccountRepository extends JpaRepository<SpecialAccount, Integer>{
    Optional<SpecialAccount> findByAccountNumber(String accountNumber);
}
