package com.desafiotecnico.nsu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiotecnico.nsu.entity.Credito;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long>{
    
    List<Credito> findByNumeroNfse(String numeroNfse);

    List<Credito> findByNumeroCredito(String numeroCredito);
}
