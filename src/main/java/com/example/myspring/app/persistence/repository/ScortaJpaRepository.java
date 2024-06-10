package com.example.myspring.app.persistence.repository;

import com.example.myspring.app.persistence.model.ScortaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ScortaJpaRepository extends JpaRepository<ScortaEntity, UUID> {

    Optional<ScortaEntity> findByIdDispositivo(String idDispositivo);
}
