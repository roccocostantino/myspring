package com.example.myspring.app.controller;

import com.example.myspring.app.dto.Tablet;
import com.example.myspring.app.service.TabletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tablet")
public class TabletRestController {

    private final TabletService tabletService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tablet> create(@RequestBody Tablet tablet) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.tabletService.create(tablet));
    }

    @GetMapping(value = "/read/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tablet> read(@PathVariable String id) {
        Tablet response = this.tabletService.read(id);
        if (response != null) return ResponseEntity.status(HttpStatus.OK).body(this.tabletService.read(id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tablet> update(@RequestBody Tablet tablet) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.tabletService.update(tablet));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        this.tabletService.delete(id);
    }
}
