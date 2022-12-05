package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "Inscripciones")
@Data
public class Inscripciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;


    private String estado;

    @ManyToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private Personas personas;

    @OneToOne(mappedBy = "inscripciones")
    private Pagos pagos;

    @ManyToOne
    @JoinColumn(name = "idModalidad",referencedColumnName = "idModalidad")
    private Modalidades modalidades;



}
