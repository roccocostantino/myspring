package com.example.myspring.app.persistence.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="cellulare")
public class CellulareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modello")
    private String modello;

    @Column(name = "data_uscita")
    private Date dataUscita;

    @Column(name = "prezzo")
    private Integer prezzo;
}
