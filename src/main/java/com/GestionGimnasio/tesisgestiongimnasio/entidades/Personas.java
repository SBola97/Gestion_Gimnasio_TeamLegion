package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Personas")
@Data
@NoArgsConstructor
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellidos;

    @Column(unique = true)
    private String cedula;

    @Column(unique = true)
    @Email
    private String email;

    private int edad;

    private String telefono;

    private float peso;

    private float estatura;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="personas")
    private Set<Disciplinas_Profesor> DisciplinasProfesor = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="personas")
    private Set<Competidores_Torneo> CompetidoresTorneo = new HashSet<>();

    @OneToOne(mappedBy = "personas",cascade = CascadeType.ALL)
    private Usuarios usuarios;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRol",referencedColumnName = "idRol")
    private Roles roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personas")
    private Set<Inscripciones> Inscripciones = new HashSet<>();



}
