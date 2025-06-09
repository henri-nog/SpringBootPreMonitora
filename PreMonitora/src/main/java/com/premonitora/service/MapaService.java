package com.premonitora.service;

import com.premonitora.model.Bairro;
import com.premonitora.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapaService {

    @Autowired
    private BairroRepository bairroRepository;

    public List<Bairro> listarTodos() {
        return bairroRepository.findAll();
    }

    public String avaliarRisco(Bairro bairro) {
        int registros = bairro.getNumRegistros();

        if (registros == 0) return "NULO";
        if (registros < 5) return "LEVE";
        if (registros < 10) return "MÉDIO";
        return "GRAVE";
    }
}
