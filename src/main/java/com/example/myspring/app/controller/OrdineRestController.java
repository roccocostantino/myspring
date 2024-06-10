package com.example.myspring.app.controller;

import com.example.myspring.app.dto.Ordine;
import com.example.myspring.app.service.OrdineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/ordine")
public class OrdineRestController {

    private final OrdineService ordineService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ordine> create(@RequestBody Ordine ordine) {
        Ordine response = this.ordineService.create(ordine);
        if (response != null) return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping(value = "/read/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ordine> read(@PathVariable String id) {
        Ordine response = this.ordineService.read(id);
        if (response != null) return ResponseEntity.status(HttpStatus.OK).body(response);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ordine> update(@RequestBody Ordine ordine) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.ordineService.update(ordine));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        this.ordineService.delete(id);
    }
}
