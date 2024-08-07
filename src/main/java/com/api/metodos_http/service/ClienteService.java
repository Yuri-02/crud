package com.api.metodos_http.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.metodos_http.classes.Cliente;
import com.api.metodos_http.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                                .orElse(null);
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Alternativa de escrever o update na camada de service
    public Cliente update(Long id, Cliente clienteExistente, Cliente clienteNovo) {

        if (clienteNovo.getNome() != null) {
            clienteExistente.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getCpf() != null) {
            clienteExistente.setCpf(clienteNovo.getCpf());
        }
        if (clienteNovo.getEndereco() != null) {
            clienteExistente.setEndereco(clienteNovo.getEndereco());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteExistente.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getEmail() != null) {
            clienteExistente.setEmail(clienteNovo.getEmail());
        }
        if (clienteNovo.getDataNascimento() != null) {
            clienteExistente.setDataNascimento(clienteNovo.getDataNascimento());
        }
        if (clienteNovo.isClienteAtivo() != clienteExistente.isClienteAtivo()) {
            clienteExistente.setClienteAtivo(clienteNovo.isClienteAtivo());
        }

        return clienteRepository.save(clienteExistente);
    }

    public Cliente delete(Long id) {
        // Delete anterior
        clienteRepository.deleteById(id);

        // Delete lógico
        Cliente cliente = getById(id);
        cliente.setClienteAtivo(false);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllAtivos() {
        return clienteRepository.findByClienteAtivoTrue();
    }
}