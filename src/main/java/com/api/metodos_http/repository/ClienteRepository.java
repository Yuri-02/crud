package com.api.metodos_http.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.metodos_http.classes.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Método para buscar somente os clientes ativos (GetAllAtivos)
    List<Cliente> findByClienteAtivoTrue();

}
