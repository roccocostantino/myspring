package com.example.myspring.app.service;

import com.example.myspring.app.dto.Ordine;
import com.example.myspring.app.dto.Scorta;
import com.example.myspring.app.exceptions.NullElementException;
import com.example.myspring.app.persistence.OrdineRepository;
import com.example.myspring.app.persistence.ScortaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.myspring.app.constants.MyMessages.*;

@RequiredArgsConstructor
@Service("ordineService")
public class OrdineService {

    private final OrdineRepository ordineRepository;
    private final ScortaRepository scortaRepository;
    private static final Logger logger
            = LoggerFactory.getLogger(OrdineService.class);

    public Ordine create(Ordine ordine) {
        logger.info("Create eseguito con ordine:\nidUtente = {}\nidDispositivo = {}\nquantita = {}",
                ordine.getIdUtente(), ordine.getIdDispositivo(), ordine.getQuantita());
        String msg = "";
        if (ordine.getIdUtente() == null) msg += EMPTY_ID_UTENTE;
        if (ordine.getIdDispositivo() == null) {
            if (!msg.isEmpty()) msg += " + ";
            msg += EMPTY_ID_DISPOSITIVO;
        }
        if (ordine.getQuantita() == null) {
            if (!msg.isEmpty()) msg += " + ";
            msg += EMPTY_QUANTITA_ORDINE;
        }
        if (!msg.isEmpty()) {
            logger.error("Errore in create = {}", msg);
            throw new NullElementException(msg);
        }
        Scorta scorta = scortaRepository.getByIdDispositivo(ordine.getIdDispositivo());
        logger.debug("Scorta in create ottenuta:\nidDispositivo = {}\nquantita' = {}", scorta.getIdDispositivo(), scorta.getQuantita());
        int differenza = scorta.getQuantita() - ordine.getQuantita();
        logger.warn("Differenza tra scorta e quantita' ordinata = {}", differenza);
        if (differenza >= 0) {
            scorta.setQuantita(differenza);
            scortaRepository.update(scorta);
            Ordine risultato = ordineRepository.create(ordine);
            logger.info("Create eseguito con successo, id nuovo ordine = {}", risultato.getIdDispositivo());
            return risultato;
        } else {
            logger.error("Error = {}", BAD_QUANTITA_ORDINE);
            throw new NullElementException(BAD_QUANTITA_ORDINE);
        }
    }

    public Ordine read(String id) {
        logger.info("Read eseguito con id = {}", id);
        if (id == null) {
            logger.error("Errore in read = {}", EMPTY_ID);
            throw new NullElementException(EMPTY_ID);
        }
        Ordine risultato = ordineRepository.read(id);
        logger.info("Read eseguito con successo, ordine:\nidUtente = {}\nidDispositivo = {}\nquantita = {}",
                risultato.getIdUtente(), risultato.getIdDispositivo(), risultato.getQuantita());
        return risultato;
    }

    public Ordine update(Ordine ordine) {
        logger.info("Update eseguito con ordine:\nidUtente = {}\nidDispositivo = {}\nquantita = {}",
                ordine.getIdUtente(), ordine.getIdDispositivo(), ordine.getQuantita());
        if (ordine.getId() == null) {
            logger.error("Errore in update = {}", EMPTY_ID);
            throw new NullElementException(EMPTY_ID);
        }
        Ordine aggiornato;
        Scorta scorta = scortaRepository.getByIdDispositivo(ordine.getIdDispositivo());
        logger.debug("Scorta in update ottenuta:\nidDispositivo = {}\nquantita' = {}", scorta.getIdDispositivo(), scorta.getQuantita());
        Ordine vecchio = ordineRepository.read(ordine.getId());
        int differenzaQuantitativoOrdine = ordine.getQuantita() - vecchio.getQuantita();
        logger.debug("Differenza tra quantita' nuovo ordine e vecchio = {}", differenzaQuantitativoOrdine);
        if (differenzaQuantitativoOrdine != 0) {
            int differenza = scorta.getQuantita() - differenzaQuantitativoOrdine;
            logger.debug("Differenza tra scorta e nuova quantita' ordinata = {}", differenza);
            if (differenza >= 0) {
                scorta.setQuantita(differenza);
                scortaRepository.update(scorta);
                aggiornato = ordineRepository.update(ordine);
                logger.info("Update eseguito con successo con quantita' aggiornata, ordine:\nidUtente = {}\nidDispositivo = {}\nquantita = {}",
                        aggiornato.getIdUtente(), aggiornato.getIdDispositivo(), aggiornato.getQuantita());
                return aggiornato;
            } else {
                logger.error("Errore in update = {}", BAD_QUANTITA_ORDINE);
                throw new NullElementException(BAD_QUANTITA_ORDINE);
            }
        } else {
            aggiornato = ordineRepository.update(ordine);
            logger.info("Update eseguito con successo senza quantita' aggiornata, ordine:\nidUtente = {}\nidDispositivo = {}\nquantita = {}",
                    aggiornato.getIdUtente(), aggiornato.getIdDispositivo(), aggiornato.getQuantita());
            return aggiornato;
        }
    }

    public void delete(String id) {
        logger.info("Delete eseguito con id = {}", id);
        if (id == null) {
            logger.error("Errore in delete = {}", EMPTY_ID);
            throw new NullElementException(EMPTY_ID);
        }
        Ordine ordine = ordineRepository.read(id);
        Scorta scorta = scortaRepository.getByIdDispositivo(ordine.getIdDispositivo());
        scorta.setQuantita(scorta.getQuantita() + ordine.getQuantita());
        Scorta scortaUpdated = scortaRepository.update(scorta);
        logger.info("Scorta aggiornata:\nidDispositivo = {}\nquantita' = {}", scortaUpdated.getIdDispositivo(), scortaUpdated.getQuantita());
        ordineRepository.delete(id);
        logger.info("Delete eseguito con successo");
    }
}
