package com.example.myspring.app.controller;

import com.example.myspring.app.dto.Cellulare;
import com.example.myspring.app.service.CellulareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cellulare")
public class CellulareRestController {

    private final CellulareService cellulareService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cellulare> create(@RequestBody Cellulare cellulare) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.cellulareService.create(cellulare));
    }

    @GetMapping(value = "/read/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cellulare> read(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cellulareService.read(id));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cellulare> update(@RequestBody Cellulare cellulare) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.cellulareService.update(cellulare));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        this.cellulareService.delete(id);
    }
}
