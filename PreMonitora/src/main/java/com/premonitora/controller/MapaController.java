package com.premonitora.controller;

import com.premonitora.model.Bairro;
import com.premonitora.service.MapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mapa")
public class MapaController {

    @Autowired
    private MapaService mapaService;

    @GetMapping("/bairros")
    public ResponseEntity<List<String>> listarBairrosComRisco() {
        List<Bairro> bairros = mapaService.listarTodos();

        List<String> resultado = bairros.stream()
                .map(b -> b.getNome() + " - RISCO: " + mapaService.avaliarRisco(b))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }
}
