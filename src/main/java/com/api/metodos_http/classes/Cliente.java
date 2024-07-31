package com.api.metodos_http.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Column(length = 11)
    private String telefone;

    @Column(unique = true)
    private String email;

    @Column(name = "data_nascimento")
    private String dataNascimento;

}
