package com.example.myspring.app.persistence.repository;

import com.example.myspring.app.persistence.model.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UtenteJpaRepository extends JpaRepository<UtenteEntity, UUID> {
}
