package com.example.myspring.app.persistence;


import com.example.myspring.app.dto.Utente;
import com.example.myspring.app.exceptions.BadInputException;
import com.example.myspring.app.persistence.model.UtenteEntity;
import com.example.myspring.app.persistence.repository.UtenteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.example.myspring.app.constants.MyMessages.BAD_ID;

@RequiredArgsConstructor
@Repository
public class UtenteRepository {

    private final UtenteJpaRepository utenteJpaRepository;

    @Transactional
    public Utente create(Utente utente) {
        UtenteEntity utenteEntity = new UtenteEntity();
        setAll(utenteEntity, utente);
        utenteJpaRepository.save(utenteEntity);
        utente.setId(utenteEntity.getId().toString());
        return utente;
    }

    public Utente read(String id) {
        Optional<UtenteEntity> entityOptional = utenteJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            UtenteEntity utenteEntity = entityOptional.get();
            return new Utente(
                    utenteEntity.getId().toString(),
                    utenteEntity.getNome(),
                    utenteEntity.getCognome(),
                    utenteEntity.getEmail()
            );
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    @Transactional
    public Utente update(Utente utente) {
        Optional<UtenteEntity> entityOptional = utenteJpaRepository.findById(UUID.fromString(utente.getId()));
        if(entityOptional.isPresent()){
            UtenteEntity utenteEntity = entityOptional.get();
            setAll(utenteEntity, utente);
            return new Utente(
                    utenteEntity.getId().toString(),
                    utenteEntity.getNome(),
                    utenteEntity.getCognome(),
                    utenteEntity.getEmail()
            );
        } else {
            throw new BadInputException(BAD_ID + utente.getId());
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<UtenteEntity> entityOptional = utenteJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            UtenteEntity utenteEntity = entityOptional.get();
            utenteJpaRepository.delete(utenteEntity);
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    private void setAll(UtenteEntity utenteEntity, Utente utente) {
        if (utente.getNome() != null) utenteEntity.setNome(utente.getNome());
        if (utente.getCognome() != null) utenteEntity.setCognome(utente.getCognome());
        if (utente.getEmail()!= null) utenteEntity.setEmail(utente.getEmail());
    }
}
