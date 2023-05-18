package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
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
    @Size(min=10, max=10, message = "La cédula debe contener 10 dígitos")
    @Column(unique = true, length = 10)
    private String cedula;

    @Column(unique = true)
    @Email
    private String email;

    private int edad;

    @NotNull
    @Column(length=10)
    private String telefono;

    private float peso;

    private float estatura;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idDisciplina", referencedColumnName = "idDisciplina")
    private Disciplinas disciplinas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="personas")
    private Set<Competidores_Torneo> CompetidoresTorneo = new HashSet<>();

    @OneToOne(mappedBy = "personas")
    private Usuarios usuarios;


    @ManyToOne
    @JoinColumn(name = "idRol",referencedColumnName = "idRol")
    private Roles roles;

    @OneToOne(mappedBy = "personas", cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    //private Set<Inscripciones> Inscripciones = new HashSet<>();
    private Inscripciones inscripciones;

}
