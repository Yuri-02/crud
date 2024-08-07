package com.api.metodos_http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.metodos_http.classes.ContaBancaria;
import com.api.metodos_http.service.ContaBancariaService;

@RestController
@RequestMapping("contas")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    // Buscar todas as contas - getAll
    @GetMapping
    public ResponseEntity<List<ContaBancaria>> getAll() {
        return ResponseEntity.ok(contaBancariaService.getAll());
    }

    // Buscar conta pela número - getById
    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contaBancariaService.getById(id));
    }

    // Criar uma conta - create
    @PostMapping
    public ResponseEntity<ContaBancaria> create(@RequestBody ContaBancaria contaBancaria) {
        return ResponseEntity.ok(contaBancariaService.create(contaBancaria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> update(@PathVariable Long id, @RequestBody ContaBancaria contaNova) {
        ContaBancaria contaExistente = contaBancariaService.getById(id);

        if (contaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(contaBancariaService.update(contaExistente, contaNova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaBancariaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
