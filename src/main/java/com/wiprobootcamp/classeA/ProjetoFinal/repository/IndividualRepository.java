package com.wiprobootcamp.classeA.ProjetoFinal.repository;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Integer> {
    Optional<Individual> findByCpf(String cpf);
}
