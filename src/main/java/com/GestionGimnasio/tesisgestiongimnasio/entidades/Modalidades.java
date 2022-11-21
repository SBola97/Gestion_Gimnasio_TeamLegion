package com.GestionGimnasio.tesisgestiongimnasio.entidades;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Modalidades")
@Data
public class Modalidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModalidad;

    @NotEmpty
    private String tipo;

    @NotEmpty
    private String nombre;

    @NotNull
    private float valor;

    @OneToOne(mappedBy = "modalidades")

    private Inscripciones inscripciones;

}
