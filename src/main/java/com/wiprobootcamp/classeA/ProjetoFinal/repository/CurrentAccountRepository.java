package com.wiprobootcamp.classeA.ProjetoFinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Integer>{
    Optional<CurrentAccount> findByAccountNumber(String accountNumber);
}
