package com.premonitora.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "perfis")
public class Perfil extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String cpf;
    private int idade;
    private String endereco;
    private String senha;

    @ManyToOne
    private Bairro bairroAtual;

    private boolean premium;

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Bairro getBairroAtual() { return bairroAtual; }
    public void setBairroAtual(Bairro bairroAtual) { this.bairroAtual = bairroAtual; }
    public boolean isPremium() { return premium; }
    public void setPremium(boolean premium) { this.premium = premium; }
}