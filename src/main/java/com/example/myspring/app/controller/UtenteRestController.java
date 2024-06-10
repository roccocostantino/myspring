package com.example.myspring.app.controller;

import com.example.myspring.app.dto.Utente;
import com.example.myspring.app.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/utente")
public class UtenteRestController {

    private final UtenteService utenteService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utente> create(@RequestBody Utente utente) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.utenteService.create(utente));
    }

    @GetMapping(value = "/read/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utente> read(@PathVariable String id) {
        Utente response = this.utenteService.read(id);
        if (response != null) return ResponseEntity.status(HttpStatus.OK).body(this.utenteService.read(id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utente> update(@RequestBody Utente utente) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.utenteService.update(utente));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        this.utenteService.delete(id);
    }
}
