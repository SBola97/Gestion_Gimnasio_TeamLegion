package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private int idCliente;

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

    private String cinturonbjj;

    private String cinturonkb;
}
