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
    public ResponseEntity<List<ContaBancaria>> getAll(){
        List<ContaBancaria> contaBancaria = contaBancariaService.getAll();
        return ResponseEntity.ok(contaBancaria);
    }

    // Buscar conta pela número - getById
    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> getById(@PathVariable Long id){
        ContaBancaria contaBancaria = contaBancariaService.getById(id);

        if (contaBancaria == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(contaBancaria);
    }

    // Criar uma conta - create
    @PostMapping
    public ResponseEntity<ContaBancaria> create(@RequestBody ContaBancaria contaBancaria){
        ContaBancaria contaBancariaSalva = contaBancariaService.create(contaBancaria);
        return ResponseEntity.ok(contaBancariaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> update(@PathVariable Long id, @RequestBody ContaBancaria contaBancaria){
        ContaBancaria contaBancariaExistente = contaBancariaService.getById(id);

        if (contaBancariaExistente == null){
            return ResponseEntity.notFound().build();
        }

        contaBancariaExistente.setTipoConta(contaBancaria.getTipoConta());
        contaBancariaExistente.setCliente(contaBancaria.getCliente());

        ContaBancaria contaBancariaSalva = contaBancariaService.create(contaBancariaExistente);

        return ResponseEntity.ok(contaBancariaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ContaBancaria contaBancaria = contaBancariaService.getById(id);

        if (contaBancaria == null){
            return ResponseEntity.notFound().build();
        }

        contaBancariaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
