package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuariosDTO {
    private int idUsuario;

    @NotEmpty(message="Se requiere el campo nombre de Usuario")
    private String nombreUsuario;

    @NotEmpty(message="Se requiere el campo contraseña")
    private String contraseña;

    private int idPersona;

    private String nombre;

    private String apellidos;

    private String idRol;

    @Email
    private String email;
}
