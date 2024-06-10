package com.example.myspring.app.repository;

import com.example.myspring.app.dto.Cellulare;
import com.example.myspring.app.persistence.model.CellulareEntity;
import com.example.myspring.app.persistence.repository.CellulareJpaRepository;
import com.example.myspring.app.persistence.CellulareRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CellulareRepositoryTests {

    @Mock
    private CellulareJpaRepository cellulareJpaRepository;

    @InjectMocks
    private CellulareRepository cellulareRepository;

    @Test
    void testCreate() {
        Cellulare cellulare = new Cellulare(null, "Samsung", "Galaxy S20", new Date(), 1000);

        when(cellulareJpaRepository.save(any(CellulareEntity.class))).thenAnswer(invocation -> {
            CellulareEntity savedEntity = invocation.getArgument(0);
            savedEntity.setId(UUID.randomUUID()); // Set a random UUID as ID
            return savedEntity;
        });

        Cellulare result = cellulareRepository.create(cellulare);

        assertNotNull(result);
        assertNotNull(result.getId());
        verify(cellulareJpaRepository).save(any(CellulareEntity.class));
    }

    @Test
    void testRead() {
        String id = "f47ac10b-58cc-4372-a567-0e02b2c3d479";

        when(cellulareJpaRepository.findById(UUID.fromString(id))).thenAnswer(invocation -> {
            CellulareEntity cellulareEntity = new CellulareEntity();
            cellulareEntity.setId(invocation.getArgument(0));
            cellulareEntity.setMarca("Samsung");
            cellulareEntity.setModello("Galaxy S20");
            cellulareEntity.setDataUscita(new Date());
            cellulareEntity.setPrezzo(1000);
            return Optional.of(cellulareEntity);
        });

        Cellulare result = cellulareRepository.read(id);

        assertNotNull(result);
        assertEquals("Samsung", result.getMarca());
        assertEquals("Galaxy S20", result.getModello());
        verify(cellulareJpaRepository).findById(UUID.fromString(id));
    }

    @Test
    void testRead_NotFound() {
        String id = "f47ac10b-58cc-4372-a567-0e02b2c3d479";

        when(cellulareJpaRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Cellulare result = cellulareRepository.read(id);

        assertNull(result);
        verify(cellulareJpaRepository).findById(UUID.fromString(id));
    }

    @Test
    void testUpdate() {
        Cellulare cellulare = new Cellulare("f47ac10b-58cc-4372-a567-0e02b2c3d479", "Samsung", "Galaxy S20", new Date(), 1000);

        when(cellulareJpaRepository.findById(UUID.fromString(cellulare.getId()))).thenAnswer(invocation -> {
            CellulareEntity cellulareEntity = new CellulareEntity();
            cellulareEntity.setId(invocation.getArgument(0));
            cellulareEntity.setMarca("Nuova");
            cellulareEntity.setModello("Nuovo S20");
            cellulareEntity.setDataUscita(new Date());
            cellulareEntity.setPrezzo(2000);
            return Optional.of(cellulareEntity);
        });

        Cellulare result = cellulareRepository.update(cellulare);

        assertNotNull(result);
        assertEquals(cellulare, result);
        verify(cellulareJpaRepository).findById(UUID.fromString(cellulare.getId()));
    }

    @Test
    void testUpdate_NotFound() {
        Cellulare cellulare = new Cellulare("f47ac10b-58cc-4372-a567-0e02b2c3d479", "Samsung", "Galaxy S20", new Date(), 1000);

        when(cellulareJpaRepository.findById(UUID.fromString(cellulare.getId()))).thenReturn(Optional.empty());

        Cellulare result = cellulareRepository.update(cellulare);

        assertNull(result);
        verify(cellulareJpaRepository).findById(UUID.fromString(cellulare.getId()));
    }

    @Test
    void testDelete() {
        String id = "f47ac10b-58cc-4372-a567-0e02b2c3d479";
        CellulareEntity cellulareEntity = new CellulareEntity();

        when(cellulareJpaRepository.findById(UUID.fromString(id))).thenAnswer(invocation -> {
            cellulareEntity.setId(invocation.getArgument(0));
            cellulareEntity.setMarca("Samsung");
            cellulareEntity.setModello("Galaxy S20");
            cellulareEntity.setDataUscita(new Date());
            cellulareEntity.setPrezzo(1000);
            return Optional.of(cellulareEntity);
        });

        cellulareRepository.delete(id);

        verify(cellulareJpaRepository).delete(cellulareEntity);
    }

    @Test
    void testDelete_NotFound() {
        String id = "f47ac10b-58cc-4372-a567-0e02b2c3d479";

        when(cellulareJpaRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        cellulareRepository.delete(id);

        verify(cellulareJpaRepository, never()).delete(any(CellulareEntity.class));
    }
}