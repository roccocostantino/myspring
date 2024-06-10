package com.example.myspring.app.persistence;

import com.example.myspring.app.dto.Ordine;
import com.example.myspring.app.exceptions.BadInputException;
import com.example.myspring.app.persistence.model.OrdineEntity;
import com.example.myspring.app.persistence.repository.OrdineJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.example.myspring.app.constants.MyMessages.BAD_ID;

@RequiredArgsConstructor
@Repository
public class OrdineRepository {

    private final OrdineJpaRepository ordineJpaRepository;

    @Transactional
    public Ordine create(Ordine ordine) {
        OrdineEntity ordineEntity = new OrdineEntity();
        setAll(ordineEntity, ordine);
        ordineJpaRepository.save(ordineEntity);
        ordine.setId(ordineEntity.getId().toString());
        return ordine;
    }

    public Ordine read(String id) {
        Optional<OrdineEntity> entityOptional = ordineJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            OrdineEntity ordineEntity = entityOptional.get();
            return new Ordine(
                    ordineEntity.getId().toString(),
                    ordineEntity.getIdUtente(),
                    ordineEntity.getIdDispositivo(),
                    ordineEntity.getQuantita(),
                    ordineEntity.getTipo()
            );
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    @Transactional
    public Ordine update(Ordine ordine) {
        Optional<OrdineEntity> entityOptional = ordineJpaRepository.findById(UUID.fromString(ordine.getId()));
        if(entityOptional.isPresent()){
            OrdineEntity ordineEntity = entityOptional.get();
            setAll(ordineEntity, ordine);
            return new Ordine(
                    ordineEntity.getId().toString(),
                    ordineEntity.getIdUtente(),
                    ordineEntity.getIdDispositivo(),
                    ordineEntity.getQuantita(),
                    ordineEntity.getTipo()
            );
        } else {
            throw new BadInputException(BAD_ID + ordine.getId());
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<OrdineEntity> entityOptional = ordineJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            OrdineEntity ordineEntity = entityOptional.get();
            ordineJpaRepository.delete(ordineEntity);
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    private void setAll(OrdineEntity ordineEntity, Ordine ordine) {
        if (ordine.getIdUtente() != null) ordineEntity.setIdUtente(ordine.getIdUtente());
        if (ordine.getIdDispositivo() != null) ordineEntity.setIdDispositivo(ordine.getIdDispositivo());
        if (ordine.getQuantita()!= null) ordineEntity.setQuantita(ordine.getQuantita());
        if (ordine.getTipo() != null) ordineEntity.setTipo(ordine.getTipo());
    }
}
