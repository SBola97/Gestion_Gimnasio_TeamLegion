package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @NotEmpty
    private String nombreUsuario;

    @NotEmpty
    private String contraseña;

    @OneToOne
    @JoinColumn(name = "idPersona",referencedColumnName = "idPersona")
    private Personas personas;


}
