package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Personas")
@Getter
@Setter
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    @NotNull
    @Column(unique = true, length = 10)
    private String cedula;

    @Column(unique = true)
    @Email
    private String email;

    @Min(5)
    @Max(100)
    private int edad;

    @NotNull
    @Column(length=10)
    private String telefono;

    @Min(30)
    @Max(250)
    private float peso;

    @Min(120)
    @Max(210)
    private float estatura;

    @OneToOne
    @JoinColumn(name = "idDisciplina", referencedColumnName = "idDisciplina")
    private Disciplinas disciplinas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="personas")
    private Set<Competidores_Torneo> CompetidoresTorneo = new HashSet<>();

    @OneToOne(mappedBy = "personas")
    private Usuarios usuarios;


    @ManyToOne
    @JoinColumn(name = "idRol",referencedColumnName = "idRol")
    private Roles roles;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personas")
    //private Set<Inscripciones> Inscripciones = new HashSet<>();
    private Inscripciones inscripciones;


}
