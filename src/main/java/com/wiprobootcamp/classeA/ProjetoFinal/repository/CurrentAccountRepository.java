package com.wiprobootcamp.classeA.ProjetoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Integer>{

}
