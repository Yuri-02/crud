package com.api.metodos_http.classes;

import java.time.LocalDateTime;

import com.api.metodos_http.tipo_transferencia.TipoTransferencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "contaBancaria_id", referencedColumnName = "id")
    private Transferencia transferencia;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    @Enumerated
    private TipoTransferencia tipoTransferencia;

    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "conta_origem_id", referencedColumnName = "numeroConta")
    private ContaBancaria contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id", referencedColumnName = "numeroConta")
    private ContaBancaria contaDestino;
}
