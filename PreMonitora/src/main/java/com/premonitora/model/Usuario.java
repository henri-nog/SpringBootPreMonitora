package com.premonitora.model;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String senha;
}
