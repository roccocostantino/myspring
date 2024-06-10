package com.example.myspring.app.service;

import com.example.myspring.app.dto.Tablet;
import com.example.myspring.app.exceptions.NullElementException;
import com.example.myspring.app.persistence.TabletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.myspring.app.constants.MyMessages.*;

@RequiredArgsConstructor
@Service("tabletService")
public class TabletService {

    private final TabletRepository tabletRepository;

    public Tablet create(Tablet tablet) {
        String msg = "";
        if (tablet.getMarca() == null) msg += EMPTY_MARCA;
        if (tablet.getModello() == null) {
            if (!msg.isEmpty()) msg += " + ";
            msg += EMPTY_MODELLO;
        }
        if (!msg.isEmpty()) throw new NullElementException(msg);
        return tabletRepository.create(tablet);
    }

    public Tablet read(String id) {
        if (id == null) throw new NullElementException(EMPTY_ID);
        return tabletRepository.read(id);
    }

    public Tablet update(Tablet tablet) {
        if (tablet.getId() == null) throw new NullElementException(EMPTY_ID);
        return tabletRepository.update(tablet);
    }

    public void delete(String id) {
        if (id == null) throw new NullElementException(EMPTY_ID);
        tabletRepository.delete(id);
    }
}
