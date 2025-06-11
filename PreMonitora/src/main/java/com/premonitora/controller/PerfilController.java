package com.premonitora.controller;

import com.premonitora.dtos.PerfilRecordDto;
import com.premonitora.model.Perfil;
import com.premonitora.repository.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @PostMapping
    public ResponseEntity<Perfil> create(@RequestBody @Valid PerfilRecordDto dto) {
        Perfil perfil = new Perfil();
        BeanUtils.copyProperties(dto, perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(perfil));
    }

    @GetMapping
    public List<Perfil> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePerfil(@PathVariable UUID id) {
        Optional<Perfil> perfil = repository.findById(id);
        if (perfil.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado"); // ✅ agora é válido
        }
        return ResponseEntity.ok(perfil.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid PerfilRecordDto dto) {
        return repository.findById(id)
                .map(perfil -> {
                    BeanUtils.copyProperties(dto, perfil);
                    return ResponseEntity.ok(repository.save(perfil));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado"));
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        return repository.findById(id).map(p -> {
            repository.delete(p);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado"));
    }
}
