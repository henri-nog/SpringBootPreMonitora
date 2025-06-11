package com.premonitora.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_bairro")
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private int numRegistros;

    public void incrementarDesabamento(int qtd) {
        this.numRegistros += qtd;
    }
}
