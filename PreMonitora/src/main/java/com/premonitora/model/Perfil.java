package com.premonitora.model;

import jakarta.persistence.*;

import java.util.Scanner;
import java.util.UUID;

@Entity
@Table(name = "tb_perfil")
public class Perfil extends Usuario {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bairro_id", referencedColumnName = "id")
    private Bairro bairroAtual;

    public Perfil() {}

    public Bairro getBairroAtual() {
        return bairroAtual;
    }

    public void setBairroAtual(Bairro bairroAtual) {
        this.bairroAtual = bairroAtual;
    }

    public void trocarSenhaInterativa(Scanner scanner) {
        System.out.print("Digite a senha atual: ");
        String senhaAtual = scanner.nextLine();
        System.out.print("Digite a nova senha: ");
        String novaSenha = scanner.nextLine();
        super.trocarSenha(senhaAtual, novaSenha);
    }

    public void assinarModoPremium(Scanner scanner) {
        System.out.println("\nDeseja assinar o Modo Premium?");
        System.out.println("\"Premium Vigilante R$ 4,99/mês\"");
        System.out.println("1. Assinar");
        System.out.println("2. Cancelar");

        String escolha = scanner.nextLine();

        if (escolha.equals("1")) {
            System.out.println("Você assinou o Modo Premium!");
        } else if (escolha.equals("2")) {
            System.out.println("Assinatura cancelada.");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
