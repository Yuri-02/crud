package com.api.metodos_http.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.metodos_http.classes.ContaBancaria;
import com.api.metodos_http.classes.Transferencia;


public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>  {

    List<Transferencia> findByContaOrigemOrContaDestinoOrderByDataDesc(
        ContaBancaria contaOrigem, 
        ContaBancaria contaDestino
    );
}
