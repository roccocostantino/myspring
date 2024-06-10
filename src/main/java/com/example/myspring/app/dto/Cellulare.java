package com.example.myspring.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Cellulare {

    private String id;
    private String marca;
    private String modello;
    private Date dataUscita;
    private Integer prezzo;
}
