package com.api.metodos_http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.metodos_http.classes.Cliente;
import com.api.metodos_http.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Buscar todos os clientes - getAll
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }
    
    // Buscar um cliente por id - getById
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    // Criar um cliente - create
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.create(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    // Atualizar um cliente - update
    // Combinação do getById e create
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteExistente = clienteService.getById(id);

        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setEndereco(cliente.getEndereco());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setDataNascimento(cliente.getDataNascimento());

        Cliente clienteSalvo = clienteService.create(clienteExistente);

        return ResponseEntity.ok(clienteSalvo);
    }

    // Deletar um cliente - delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
    
}
