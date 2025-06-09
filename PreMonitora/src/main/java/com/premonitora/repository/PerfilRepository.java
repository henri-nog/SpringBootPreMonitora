package com.premonitora.repository;

import com.premonitora.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
    // você pode adicionar métodos personalizados aqui, se quiser
    Perfil findByCpf(String cpf);
}
