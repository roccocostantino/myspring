package com.example.myspring.app.service;

import com.example.myspring.app.dto.Utente;
import com.example.myspring.app.exceptions.NullElementException;
import com.example.myspring.app.persistence.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.myspring.app.constants.MyMessages.*;

@RequiredArgsConstructor
@Service("utenteService")
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public Utente create(Utente utente) {
        String msg = "";
        if (utente.getNome() == null) msg += EMPTY_NOME;
        if (utente.getEmail() == null) {
            if (!msg.isEmpty()) msg += " + ";
            msg += EMPTY_EMAIL;
        }
        if (!msg.isEmpty()) throw new NullElementException(msg);
        return utenteRepository.create(utente);
    }

    public Utente read(String id) {
        if (id == null) throw new NullElementException(EMPTY_ID);
        return utenteRepository.read(id);
    }

    public Utente update(Utente utente) {
        if (utente.getId() == null) throw new NullElementException(EMPTY_ID);
        return utenteRepository.update(utente);
    }

    public void delete(String id) {
        if (id == null) throw new NullElementException(EMPTY_ID);
        utenteRepository.delete(id);
    }
}
