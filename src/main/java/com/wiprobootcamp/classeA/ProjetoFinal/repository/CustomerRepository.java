package com.wiprobootcamp.classeA.ProjetoFinal.repository;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByDocumentNumber(String documentNumber);
}
