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
    PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<Perfil> savePerfil(@RequestBody @Valid PerfilRecordDto perfilDto) {
        var perfil = new Perfil();
        BeanUtils.copyProperties(perfilDto, perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilRepository.save(perfil));
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> getAllPerfis() {
        return ResponseEntity.status(HttpStatus.OK).body(perfilRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePerfil(@PathVariable(value = "id") UUID id) {
        Optional<Perfil> perfilOp = perfilRepository.findById(id);
        if (perfilOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(perfilOp.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerfil(@PathVariable(value = "id") UUID id, @RequestBody @Valid PerfilRecordDto perfilDto) {
        Optional<Perfil> perfilOp = perfilRepository.findById(id);
        if (perfilOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }
        var perfil = perfilOp.get();
        BeanUtils.copyProperties(perfilDto, perfil);
        return ResponseEntity.status(HttpStatus.OK).body(perfilRepository.save(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerfil(@PathVariable(value = "id") UUID id) {
        Optional<Perfil> perfilOp = perfilRepository.findById(id);
        if (perfilOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }
        perfilRepository.delete(perfilOp.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
