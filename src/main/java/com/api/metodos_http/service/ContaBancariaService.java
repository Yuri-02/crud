package com.api.metodos_http.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.metodos_http.classes.ContaBancaria;
import com.api.metodos_http.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public List<ContaBancaria> getAll(){
        return contaBancariaRepository.findAll();
    }

    public ContaBancaria getById(Long id) {
        return contaBancariaRepository.findById(id)
                                .orElse(null);
    }

    public ContaBancaria create(ContaBancaria contaBancaria){
        return contaBancariaRepository.save(contaBancaria);
    }

    // public ContaBancaria update(Long id, ContaBancaria contaBancaria){
    //     ContaBancaria contaBancariaExistente = getById(id);

    //     if (contaBancariaExistente == null){
    //         return null;
    //     }

    //     contaBancariaExistente.setTipoConta(contaBancaria.getTipoConta());
    // }

}
