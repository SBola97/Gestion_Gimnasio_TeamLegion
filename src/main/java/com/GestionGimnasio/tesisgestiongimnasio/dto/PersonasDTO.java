package com.GestionGimnasio.tesisgestiongimnasio.dto;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonasDTO {

    private int idPersona;

    private String nombre;

    private String apellidos;

    private String cedula;

    private String email;

    private int edad;

    private String telefono;

    private float peso;

    private float estatura;

    private int idRol;

}
