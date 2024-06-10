package com.example.myspring.app.persistence.repository;

import com.example.myspring.app.persistence.model.OrdineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdineJpaRepository extends JpaRepository<OrdineEntity, UUID> {
}
