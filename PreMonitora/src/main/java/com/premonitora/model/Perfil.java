package com.premonitora.model;
import jakarta.persistence.*;



@Entity
@Table(name = "tb_perfil")
public class Perfil extends Usuario {

    @ManyToOne
    @JoinColumn(name = "bairro_id", nullable = true)
    private Bairro bairroAtual;
}
