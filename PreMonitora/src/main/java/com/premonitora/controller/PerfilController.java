package com.premonitora.controller;

import com.premonitora.dtos.PerfilRecordDto;
import com.premonitora.model.Perfil;
import com.premonitora.repository.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilRepository repository;

    public PerfilController(PerfilRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid PerfilRecordDto dto) {
        Perfil perfil = new Perfil();
        BeanUtils.copyProperties(dto, perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(perfil));
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        return repository.findById(id)
                .map(perfil -> {
                    repository.delete(perfil);
                    return ResponseEntity.ok((Object) "Perfil deletado com sucesso");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body((Object) "Perfil não encontrado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePerfil(@PathVariable UUID id) {
        return repository.findById(id)
                .map(perfil -> ResponseEntity.ok((Object) perfil))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body((Object) "Perfil não encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid PerfilRecordDto dto) {
        return repository.findById(id)
                .map(perfil -> {
                    BeanUtils.copyProperties(dto, perfil);
                    Perfil perfilAtualizado = repository.save(perfil);
                    return ResponseEntity.ok((Object) perfilAtualizado);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body((Object) "Perfil não encontrado"));
    }
}