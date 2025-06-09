package com.premonitora.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_bairro")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private int numRegistros;

    // Construtores
    public Bairro() {}

    public Bairro(String nome, int numRegistros) {
        this.nome = nome;
        this.numRegistros = numRegistros;
    }

    // Getters e Setters
    public UUID getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getNumRegistros() { return numRegistros; }
    public void setNumRegistros(int numRegistros) { this.numRegistros = numRegistros; }

    public void incrementarDesabamento(int quantidade) {
        this.numRegistros += quantidade;
    }

    @Override
    public String toString() {
        return nome + " (Registros: " + numRegistros + ")";
    }
}
