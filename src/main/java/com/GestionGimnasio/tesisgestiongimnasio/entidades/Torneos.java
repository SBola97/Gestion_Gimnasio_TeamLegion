package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Torneos")
@Getter
@Setter
public class Torneos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTorneo;

    @NotNull
    @NotEmpty
    private String nombre;

    @NotNull
    private LocalDate fecha;

    @NotNull
    @NotEmpty
    private String ciudad;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "torneos")
    private Set<Competidores_Torneo> CompetidoresTorneo = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "idDisciplina", referencedColumnName = "idDisciplina")
    private Disciplinas disciplinas;
}
