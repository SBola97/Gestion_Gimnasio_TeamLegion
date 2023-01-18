package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Disciplinas")
@Getter
@Setter
public class Disciplinas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDisciplina;

    @NotEmpty
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplinas")
    private Set<Disciplinas_Profesor> DisciplinasProfesor = new HashSet<>();

    @OneToMany(mappedBy= "disciplinas")
    private Set<Torneos> torneos = new HashSet<>();




}
