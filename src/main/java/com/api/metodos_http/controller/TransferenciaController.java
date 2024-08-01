package com.api.metodos_http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.metodos_http.classes.Transferencia;
import com.api.metodos_http.service.TransferenciaService;

@RestController
@RequestMapping("transferencias")
public class TransferenciaController {

    @Autowired
    private Transferencia transferenciaService;


    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> getById(@PathVariable Long id){
        Transferencia transferencia = transferenciaService.getById(id);

        if(transferencia == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transferencia);
    }

    @PostMapping
    public ResponseEntity<Transferencia> create(@RequestBody Transferencia transferencia){
        Transferencia transferenciaSalva = transferenciaService.create(transferencia);
        return ResponseEntity.ok(transferenciaSalva);
    }

}
