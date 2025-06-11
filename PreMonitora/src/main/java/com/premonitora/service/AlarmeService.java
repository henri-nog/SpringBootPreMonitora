package com.premonitora.service;

import org.springframework.stereotype.Service;

@Service
public class AlarmeService {
    public String exibirAlerta(int numeroRegistros) {
        if (numeroRegistros == 0) {
            return "Atualmente, NÃO há risco na área. Fique tranquilo(a).";
        } else if (numeroRegistros < 5) {
            return "Sua área possui risco LEVE.";
        } else if (numeroRegistros > 5 && numeroRegistros < 10) {
            return "Sua área possui risco MÉDIO.";
        } else {
            return "ATENÇÃO!!! ALTO RISCO em sua área!";
        }
    }
    public String chamarSAMU() { return "\"O SAMU está a caminho\"."; }
    public String chamarBombeiros() { return "\"Os Bombeiros estão a caminho\"."; }
    public String chamarDefesaCivil() { return "\"A Defesa Civil está a caminho\"."; }
}
