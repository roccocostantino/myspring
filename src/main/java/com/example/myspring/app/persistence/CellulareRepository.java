package com.example.myspring.app.persistence;

import com.example.myspring.app.dto.Cellulare;
import com.example.myspring.app.exceptions.BadInputException;
import com.example.myspring.app.persistence.model.CellulareEntity;
import com.example.myspring.app.persistence.repository.CellulareJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.example.myspring.app.constants.MyMessages.*;

@RequiredArgsConstructor
@Repository
public class CellulareRepository {

    private final CellulareJpaRepository cellulareJpaRepository;

    @Transactional
    public Cellulare create(Cellulare cellulare) {
        CellulareEntity cellulareEntity = new CellulareEntity();
        setAll(cellulareEntity, cellulare);
        cellulareJpaRepository.save(cellulareEntity);
        cellulare.setId(cellulareEntity.getId().toString());
        return cellulare;
    }

    public Cellulare read(String id) throws BadInputException {
        Optional<CellulareEntity> entityOptional = cellulareJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            CellulareEntity cellulareEntity = entityOptional.get();
            return new Cellulare(
                    cellulareEntity.getId().toString(),
                    cellulareEntity.getMarca(),
                    cellulareEntity.getModello(),
                    cellulareEntity.getDataUscita(),
                    cellulareEntity.getPrezzo()
            );
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    @Transactional
    public Cellulare update(Cellulare cellulare) throws BadInputException {
        Optional<CellulareEntity> entityOptional = cellulareJpaRepository.findById(UUID.fromString(cellulare.getId()));
        if(entityOptional.isPresent()){
            CellulareEntity cellulareEntity = entityOptional.get();
            setAll(cellulareEntity, cellulare);
            return new Cellulare(
                    cellulareEntity.getId().toString(),
                    cellulareEntity.getMarca(),
                    cellulareEntity.getModello(),
                    cellulareEntity.getDataUscita(),
                    cellulareEntity.getPrezzo()
            );
        } else {
            throw new BadInputException(BAD_ID + cellulare.getId());
        }
    }

    @Transactional
    public void delete(String id) throws BadInputException {
        Optional<CellulareEntity> entityOptional = cellulareJpaRepository.findById(UUID.fromString(id));
        if(entityOptional.isPresent()) {
            CellulareEntity cellulareEntity = entityOptional.get();
            cellulareJpaRepository.delete(cellulareEntity);
        } else {
            throw new BadInputException(BAD_ID + id);
        }
    }

    private void setAll(CellulareEntity cellulareEntity, Cellulare cellulare) {
        if (cellulare.getDataUscita() != null) cellulareEntity.setDataUscita(cellulare.getDataUscita());
        if (cellulare.getMarca() != null) cellulareEntity.setMarca(cellulare.getMarca());
        if (cellulare.getModello()!= null) cellulareEntity.setModello(cellulare.getModello());
        if (cellulare.getPrezzo() != null) cellulareEntity.setPrezzo(cellulare.getPrezzo());
    }
}
