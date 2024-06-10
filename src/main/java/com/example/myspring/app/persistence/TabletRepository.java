package com.example.myspring.app.persistence;

import com.example.myspring.app.dto.Tablet;
import com.example.myspring.app.exceptions.BadInputException;
import com.example.myspring.app.persistence.model.TabletEntity;
import com.example.myspring.app.persistence.repository.TabletJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.example.myspring.app.constants.MyMessages.BAD_ID;

@RequiredArgsConstructor
@Repository
public class TabletRepository {

    private final TabletJpaRepository tabletJpaRepository;

    @Transactional
    public Tablet create(Tablet tablet) {
        TabletEntity tabletEntity = new TabletEntity();
        setAll(tabletEntity, tablet);
        tabletJpaRepository.save(tabletEntity);
        tablet.setId(tabletEntity.getId().toString());
        return tablet;
    }

    public Tablet read(String id) {
        Optional<TabletEntity> entityOptional = tabletJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            TabletEntity tabletEntity = entityOptional.get();
            return new Tablet(
                    tabletEntity.getId().toString(),
                    tabletEntity.getMarca(),
                    tabletEntity.getModello(),
                    tabletEntity.getDataUscita(),
                    tabletEntity.getPrezzo()
            );
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    @Transactional
    public Tablet update(Tablet tablet) {
        Optional<TabletEntity> entityOptional = tabletJpaRepository.findById(UUID.fromString(tablet.getId()));
        if(entityOptional.isPresent()){
            TabletEntity tabletEntity = entityOptional.get();
            setAll(tabletEntity, tablet);
            return new Tablet(
                    tabletEntity.getId().toString(),
                    tabletEntity.getMarca(),
                    tabletEntity.getModello(),
                    tabletEntity.getDataUscita(),
                    tabletEntity.getPrezzo()
            );
        } else {
            throw new BadInputException(BAD_ID + tablet.getId());
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<TabletEntity> entityOptional = tabletJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            TabletEntity tabletEntity = entityOptional.get();
            tabletJpaRepository.delete(tabletEntity);
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    private void setAll(TabletEntity tabletEntity, Tablet tablet) {
        if (tablet.getDataUscita() != null) tabletEntity.setDataUscita(tablet.getDataUscita());
        if (tablet.getMarca() != null) tabletEntity.setMarca(tablet.getMarca());
        if (tablet.getModello()!= null) tabletEntity.setModello(tablet.getModello());
        if (tablet.getPrezzo() != null) tabletEntity.setPrezzo(tablet.getPrezzo());
    }
}
