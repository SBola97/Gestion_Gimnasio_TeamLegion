package com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Disciplinas")
@Data
public class Disciplinas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDisciplina;

    @NotEmpty
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplinas")
    private Set<Disciplinas_Profesor> DisciplinasProfesor = new HashSet<>();

    @OneToMany(mappedBy= "disciplinas")
    @JsonIgnore
    private Set<Torneos> torneos = new HashSet<>();




}
