package com.premonitora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.premonitora.model.Perfil;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {}
