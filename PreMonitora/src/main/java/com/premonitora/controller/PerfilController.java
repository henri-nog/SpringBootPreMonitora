package com.premonitora.controller;

import com.premonitora.dtos.PerfilRecordDto;
import com.premonitora.model.Bairro;
import com.premonitora.model.Perfil;
import com.premonitora.repository.BairroRepository;
import com.premonitora.repository.PerfilRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    BairroRepository bairroRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<Perfil> savePerfil(@RequestBody @Valid PerfilRecordDto dto) {
        var perfil = new Perfil();
        BeanUtils.copyProperties(dto, perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilRepository.save(perfil));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Perfil>> getAllPerfis() {
        return ResponseEntity.ok(perfilRepository.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPerfil(@PathVariable UUID id) {
        var op = perfilRepository.findById(id);
        return op.<ResponseEntity<Object>>map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerfil(@PathVariable UUID id, @RequestBody @Valid PerfilRecordDto dto) {
        Optional<Perfil> op = perfilRepository.findById(id);
        if (op.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        var perfil = op.get();
        BeanUtils.copyProperties(dto, perfil);
        return ResponseEntity.ok(perfilRepository.save(perfil));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerfil(@PathVariable UUID id) {
        Optional<Perfil> op = perfilRepository.findById(id);
        if (op.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        perfilRepository.delete(op.get());
        return ResponseEntity.noContent().build();
    }

    // Trocar bairro atual do perfil
    @PatchMapping("/{id}/bairro")
    public ResponseEntity<Object> setBairroPerfil(@PathVariable UUID id, @RequestParam String nomeBairro) {
        Optional<Perfil> perfilOp = perfilRepository.findById(id);
        Optional<Bairro> bairroOp = bairroRepository.findByNomeIgnoreCase(nomeBairro);
        if (perfilOp.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        if (bairroOp.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bairro não encontrado");
        Perfil perfil = perfilOp.get();
        perfil.setBairroAtual(bairroOp.get());
        perfilRepository.save(perfil);
        return ResponseEntity.ok(perfil);
    }

    // Trocar senha do perfil
    @PatchMapping("/{id}/senha")
    public ResponseEntity<Object> trocarSenha(@PathVariable UUID id, @RequestParam String senhaAtual, @RequestParam String novaSenha) {
        Optional<Perfil> op = perfilRepository.findById(id);
        if (op.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        Perfil perfil = op.get();
        if (!perfil.getSenha().equals(senhaAtual))
            return ResponseEntity.badRequest().body("Senha atual incorreta.");
        perfil.setSenha(novaSenha);
        perfilRepository.save(perfil);
        return ResponseEntity.ok("Senha alterada com sucesso!");
    }

    // Assinar ou cancelar premium
    @PatchMapping("/{id}/premium")
    public ResponseEntity<Object> assinarPremium(@PathVariable UUID id, @RequestParam boolean premium) {
        Optional<Perfil> op = perfilRepository.findById(id);
        if (op.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        Perfil perfil = op.get();
        perfil.setPremium(premium);
        perfilRepository.save(perfil);
        return ResponseEntity.ok(perfil);
    }
}