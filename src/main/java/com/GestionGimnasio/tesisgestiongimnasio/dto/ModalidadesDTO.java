package com.GestionGimnasio.tesisgestiongimnasio.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ModalidadesDTO {

    private int idModalidad;

    @NotEmpty(message = "El campo tipo es requerido")
    private String tipo;

    @NotEmpty(message = "El campo nombre es requerido")
    private String nombre;

    @NotNull(message = "El campo valor es requerido")
    private float valor;
}
