package com.example.myspring.app.service;

import com.example.myspring.app.dto.Cellulare;
import com.example.myspring.app.exceptions.BadInputException;
import com.example.myspring.app.exceptions.NullElementException;
import com.example.myspring.app.persistence.CellulareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.myspring.app.constants.MyMessages.*;

@RequiredArgsConstructor
@Service("cellulareService")
public class CellulareService {

    private final CellulareRepository cellulareRepository;

    public Cellulare create(Cellulare cellulare) throws NullElementException {
        String msg = "";
        if (cellulare.getMarca() == null) msg += EMPTY_MARCA;
        if (cellulare.getModello() == null) {
            if (!msg.isEmpty()) msg += " + ";
            msg += EMPTY_MODELLO;
        }
        if (!msg.isEmpty()) throw new NullElementException(msg);
        return cellulareRepository.create(cellulare);
    }

    public Cellulare read(String id) throws BadInputException, NullElementException {
        if (id == null) throw new NullElementException(EMPTY_ID);
        return cellulareRepository.read(id);
    }

    public Cellulare update(Cellulare cellulare) throws BadInputException, NullElementException {
        if (cellulare.getId() == null) throw new NullElementException(EMPTY_ID);
        return cellulareRepository.update(cellulare);
    }

    public void delete(String id) throws BadInputException, NullElementException {
        if (id == null) throw new NullElementException(EMPTY_ID);
        cellulareRepository.delete(id);
    }
}
