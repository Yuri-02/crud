package com.api.metodos_http.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.metodos_http.classes.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
