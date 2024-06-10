package com.example.myspring.app.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="ordine")
public class OrdineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "id_utente")
    private String idUtente;

    @Column(name = "id_dispositivo")
    private String idDispositivo;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "tipo")
    private Integer tipo;
}
