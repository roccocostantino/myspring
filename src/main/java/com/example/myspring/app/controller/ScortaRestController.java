package com.example.myspring.app.controller;

import com.example.myspring.app.dto.Scorta;
import com.example.myspring.app.service.ScortaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/scorta")
public class ScortaRestController {

    private final ScortaService scortaService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Scorta> create(@RequestBody Scorta scorta) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.scortaService.create(scorta));
    }

    @GetMapping(value = "/read/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Scorta> read(@PathVariable String id) {
        Scorta response = this.scortaService.read(id);
        if (response != null) return ResponseEntity.status(HttpStatus.OK).body(this.scortaService.read(id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Scorta> update(@RequestBody Scorta scorta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.scortaService.update(scorta));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        this.scortaService.delete(id);
    }
}
