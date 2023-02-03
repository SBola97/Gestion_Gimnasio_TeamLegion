package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class PersonasDTO {

    private int idPersona;

    @NotNull(message = "El campo nombre es requerido")
    private String nombre;

    @NotNull(message = "El campo apellidos es requerido")
    private String apellidos;

    @NotNull(message = "El campo cedula es requerido")
    private String cedula;


    @Email
    private String email;


    private int edad;

    @NotNull(message = "El campo telefono es requerido")
    private String telefono;


    private float peso;


    private float estatura;

    private int idRol;

    private Set<CompetidoresTorneoDTO> competidoresTorneo;



}
