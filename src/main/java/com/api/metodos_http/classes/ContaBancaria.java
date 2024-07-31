package com.api.metodos_http.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "contas")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_da_conta")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(nullable = false)
    private String tipoConta;

}
