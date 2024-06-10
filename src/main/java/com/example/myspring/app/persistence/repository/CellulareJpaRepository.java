package com.example.myspring.app.persistence.repository;

import com.example.myspring.app.persistence.model.CellulareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CellulareJpaRepository extends JpaRepository<CellulareEntity, UUID> {
}
