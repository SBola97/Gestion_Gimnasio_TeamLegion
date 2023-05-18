package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "Inscripciones")
@Getter
@Setter
public class Inscripciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @NotNull
    private String estado;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private Personas personas;

    @OneToOne(mappedBy = "inscripciones",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    private Pagos pagos;

    @ManyToOne
    @JoinColumn(name = "idModalidad",referencedColumnName = "idModalidad")
    private Modalidades modalidades;



}
