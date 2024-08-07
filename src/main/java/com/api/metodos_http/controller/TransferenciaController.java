package com.api.metodos_http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.metodos_http.classes.Transferencia;
import com.api.metodos_http.service.ContaBancariaService;
import com.api.metodos_http.service.TransferenciaService;

@RestController
@RequestMapping("transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @Autowired
    private ContaBancariaService contaBancariaService;

    @GetMapping
    public ResponseEntity<List<Transferencia>> getAll() {
        return ResponseEntity.ok(transferenciaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transferenciaService.getById(id));
    }

    @GetMapping("/extrato/{id}")
    public ResponseEntity<List<Transferencia>> getExtrato (@PathVariable Long idConta) {
        List<Transferencia> extrato = transferenciaService.getExtrato(idConta);
        
        if (extrato.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(extrato);
    }

    @PostMapping
    public ResponseEntity<Transferencia> create(@RequestBody Transferencia transferencia) {
        // Verificar se alguma das contas da transação são nulas
        if(contaBancariaService.temSaldo(transferencia)){
            return ResponseEntity.ok(transferenciaService.create(transferencia));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transferencia> update(@PathVariable Long id, @RequestBody Transferencia transferencia) {
        return ResponseEntity.ok(transferenciaService.update(id, transferencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transferenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
