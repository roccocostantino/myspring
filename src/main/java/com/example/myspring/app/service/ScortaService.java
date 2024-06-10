package com.example.myspring.app.service;

import com.example.myspring.app.dto.Scorta;
import com.example.myspring.app.exceptions.NullElementException;
import com.example.myspring.app.persistence.ScortaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.myspring.app.constants.MyMessages.*;

@RequiredArgsConstructor
@Service("scortaService")
public class ScortaService {

    private final ScortaRepository scortaRepository;

    public Scorta create(Scorta scorta) {
        String msg = "";
        if (scorta.getIdDispositivo() == null) msg += EMPTY_ID_DISPOSITIVO;
        if (scorta.getQuantita() == null) {
            if (!msg.isEmpty()) msg += " + ";
            msg += EMPTY_QUANTITA;
        }
        if (!msg.isEmpty()) throw new NullElementException(msg);
        return scortaRepository.create(scorta);
    }

    public Scorta read(String id) {
        if (id == null) throw new NullElementException(EMPTY_ID);
        return scortaRepository.read(id);
    }

    public Scorta update(Scorta scorta) {
        if (scorta.getId() == null) throw new NullElementException(EMPTY_ID);
        return scortaRepository.update(scorta);
    }

    public void delete(String id) {
        if (id == null) throw new NullElementException(EMPTY_ID);
        scortaRepository.delete(id);
    }
}
