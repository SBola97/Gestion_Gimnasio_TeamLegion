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

    @NotNull(message = "El campo nombre es requerido")
    private String nombre;

    @NotNull(message = "El campo apellidos es requerido")
    private String apellidos;

    @NotNull(message = "El campo cedula es requerido")
    private String cedula;

    @Email
    private String email;

    private String disciplina;

    @NotNull(message = "El campo telefono es requerido")
    private String telefono;

}
