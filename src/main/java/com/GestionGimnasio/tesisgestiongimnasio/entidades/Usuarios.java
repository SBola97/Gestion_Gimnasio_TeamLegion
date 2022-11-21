package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
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
