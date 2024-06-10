package com.example.myspring.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Utente {

    private String id;
    private String nome;
    private String cognome;
    private String email;
}
