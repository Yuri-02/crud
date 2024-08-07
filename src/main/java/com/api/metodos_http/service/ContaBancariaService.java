package com.api.metodos_http.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.metodos_http.classes.ContaBancaria;
import com.api.metodos_http.classes.Transferencia;
import com.api.metodos_http.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    ContaBancariaService(ContaBancariaRepository contaBancariaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
    }

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

    public ContaBancaria update(ContaBancaria contaExistente, ContaBancaria contaNova) {

        contaExistente.setSaldo(contaNova.getSaldo());

        return contaBancariaRepository.save(contaExistente);
    }

    public ContaBancaria delete(Long id) {
        ContaBancaria contaBancaria = getById(id);
        contaBancariaRepository.delete(contaBancaria);
        return contaBancaria;
    }

    public boolean temSaldo(Transferencia transferencia) {
        // Comprarar o saldo da conta origem com o valor da transferencia
        ContaBancaria conta = getById(transferencia.getContaOrigem().getNumeroConta());

        boolean temSaldo = (
            conta.getSaldo() 
            >= 
            transferencia.getValor()
        );
        return temSaldo;
    }
}
