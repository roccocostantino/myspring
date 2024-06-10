package com.example.myspring.app.persistence;

import com.example.myspring.app.dto.Scorta;
import com.example.myspring.app.exceptions.BadInputException;
import com.example.myspring.app.persistence.model.ScortaEntity;
import com.example.myspring.app.persistence.repository.ScortaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.example.myspring.app.constants.MyMessages.BAD_ID;
import static com.example.myspring.app.constants.MyMessages.BAD_ID_DISPOSITIVO;

@RequiredArgsConstructor
@Repository
public class ScortaRepository {

    private final ScortaJpaRepository scortaJpaRepository;

    @Transactional
    public Scorta create(Scorta scorta) {
        ScortaEntity scortaEntity = new ScortaEntity();
        setAll(scortaEntity, scorta);
        scortaJpaRepository.save(scortaEntity);
        scorta.setId(scortaEntity.getId().toString());
        return scorta;
    }

    public Scorta read(String id) {
        Optional<ScortaEntity> entityOptional = scortaJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            ScortaEntity scortaEntity = entityOptional.get();
            return new Scorta(
                    scortaEntity.getId().toString(),
                    scortaEntity.getIdDispositivo(),
                    scortaEntity.getQuantita()
            );
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    @Transactional
    public Scorta update(Scorta scorta) {
        Optional<ScortaEntity> entityOptional = scortaJpaRepository.findById(UUID.fromString(scorta.getId()));
        if(entityOptional.isPresent()){
            ScortaEntity scortaEntity = entityOptional.get();
            setAll(scortaEntity, scorta);
            return new Scorta(
                    scortaEntity.getId().toString(),
                    scortaEntity.getIdDispositivo(),
                    scortaEntity.getQuantita()
            );
        } else {
            throw new BadInputException(BAD_ID + scorta.getId());
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<ScortaEntity> entityOptional = scortaJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            ScortaEntity scortaEntity = entityOptional.get();
            scortaJpaRepository.delete(scortaEntity);
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    public Scorta getByIdDispositivo(String id) {
        ScortaEntity entity = scortaJpaRepository.findByIdDispositivo(id).orElseThrow(() -> new BadInputException(BAD_ID_DISPOSITIVO));
        return new Scorta(entity.getId().toString(), entity.getIdDispositivo(), entity.getQuantita());
    }

    private void setAll(ScortaEntity scortaEntity, Scorta scorta) {
        if (scorta.getIdDispositivo() != null) scortaEntity.setIdDispositivo(scorta.getIdDispositivo());
        if (scorta.getQuantita()!= null) scortaEntity.setQuantita(scorta.getQuantita());
    }
}
