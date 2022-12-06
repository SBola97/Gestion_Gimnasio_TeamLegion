package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Torneos")
@Data
public class Torneos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTorneo;

    @NotEmpty
    private String nombre;

    @NotNull
    private LocalDate fecha;

    @NotEmpty
    private String ciudad;

    private String categoriaPeso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "torneos")
    private Set<Competidores_Torneo> CompetidoresTorneo = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "idDisciplina", referencedColumnName = "idDisciplina")
    private Disciplinas disciplinas;
}
