package com.example.myspring.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ordine {

    private String id;
    private String idUtente;
    private String idDispositivo;
    private Integer quantita;
    private Integer tipo;
}
