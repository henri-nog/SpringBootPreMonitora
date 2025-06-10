package com.premonitora.controller;

import com.premonitora.service.AlarmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alarme")
public class AlarmeController {

    @Autowired
    AlarmeService alarmeService;

    @GetMapping("/alerta")
    public String exibirAlerta(@RequestParam int numRegistros) {
        return alarmeService.exibirAlerta(numRegistros);
    }

    @GetMapping("/samu")
    public String chamarSAMU() { return alarmeService.chamarSAMU(); }

    @GetMapping("/bombeiros")
    public String chamarBombeiros() { return alarmeService.chamarBombeiros(); }

    @GetMapping("/defesa-civil")
    public String chamarDefesaCivil() { return alarmeService.chamarDefesaCivil(); }
}