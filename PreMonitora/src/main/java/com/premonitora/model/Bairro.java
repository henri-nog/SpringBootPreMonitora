package com.premonitora.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bairros")
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private int numRegistros;

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getNumRegistros() { return numRegistros; }
    public void setNumRegistros(int numRegistros) { this.numRegistros = numRegistros; }

    public void incrementarDesabamento(int quantidade) {
        this.numRegistros += quantidade;
    }

    @Override
    public String toString() {
        return nome + " (Nº de Registros: " + numRegistros + ")";
    }
}