package com.api.metodos_http.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.metodos_http.classes.Transferencia;


public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>  {

}
