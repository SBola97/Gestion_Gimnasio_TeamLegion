package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuarios")
@Data
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @NotEmpty
    private String nombreUsuario;

    @NotEmpty
    private String contraseña;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPersona",referencedColumnName = "idPersona")
    private Personas personas;


}
