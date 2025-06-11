package com.premonitora.repository;

import com.premonitora.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, UUID> {}
