package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfesorDTO {

    private int idProfesor;

    @NotEmpty(message = "El campo nombre es requerido")
    private String nombre;

    @NotEmpty(message = "El campo apellidos es requerido")
    private String apellidos;

    @NotEmpty(message = "El campo cedula es requerido")
    private String cedula;

    @NotEmpty(message = "El campo email es requerido")
    @Email
    private String email;

    @NotNull(message = "El campo edad es requerido")
    private int edad;

    @NotEmpty(message = "El campo telefono es requerido")
    private String telefono;




}
