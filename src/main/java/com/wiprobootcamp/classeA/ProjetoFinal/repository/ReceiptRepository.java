package com.wiprobootcamp.classeA.ProjetoFinal.repository;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
}
