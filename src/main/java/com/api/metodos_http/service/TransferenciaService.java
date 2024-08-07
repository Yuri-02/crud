package com.api.metodos_http.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.metodos_http.classes.Transferencia;
import com.api.metodos_http.repository.TransferenciaRepository;
import com.api.metodos_http.classes.ContaBancaria;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    ContaBancariaService contaBancariaService;

    public List<Transferencia> getAll() {
        return transferenciaRepository.findAll();
    }

    public Transferencia getById(Long id) {
        return transferenciaRepository.findById(id).orElse(null);
    }

    public List<Transferencia> getExtrato(Long id) {
        // Tratar possível erro de retorno null
        ContaBancaria conta = contaBancariaService.getById(id);

        return transferenciaRepository.findByContaOrigemOrContaDestinoOrderByDataDesc(conta, conta);
    }

    public Transferencia create(Transferencia novaTransferencia) {
        // Tratar a possibilidade de valores nulos nos atributos da transferencia
        Transferencia transferencia = new Transferencia();

        ContaBancaria contaOrigem = contaBancariaService.getById(novaTransferencia.getContaOrigem().getNumeroConta());
        ContaBancaria contaDestino = contaBancariaService.getById(novaTransferencia.getContaDestino().getNumeroConta());

        contaOrigem.sacar(novaTransferencia.getValor());
        contaDestino.depositar(novaTransferencia.getValor());

        contaBancariaService.create(contaDestino);
        contaBancariaService.create(contaOrigem);
        
        transferencia.setValor(novaTransferencia.getValor());
        transferencia.setContaDestino(contaBancariaService.getById(novaTransferencia.getContaDestino().getNumeroConta()));
        transferencia.setContaOrigem(contaBancariaService.getById(novaTransferencia.getContaOrigem().getNumeroConta()));
        transferencia.setTipoTransferencia(novaTransferencia.getTipoTransferencia());

        return transferenciaRepository.save(transferencia);
    }

    public Transferencia update(Long id, Transferencia novaTransferencia) {
        Transferencia transferencia = transferenciaRepository.findById(id).orElse(null);
        if (novaTransferencia == null) {
            return null;
        }
        // Incluir os atributos que deseja atualizar
        return transferenciaRepository.save(transferencia);
    }

    public void delete(Long id) {
        transferenciaRepository.deleteById(id);
    }

}
