package com.api.metodos_http.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.metodos_http.classes.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

}
