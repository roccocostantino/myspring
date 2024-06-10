package com.example.myspring.app.persistence.repository;

import com.example.myspring.app.persistence.model.TabletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TabletJpaRepository extends JpaRepository<TabletEntity, UUID> {
}
