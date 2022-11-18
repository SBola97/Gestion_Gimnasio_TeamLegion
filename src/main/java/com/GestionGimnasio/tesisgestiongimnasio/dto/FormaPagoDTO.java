package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class FormaPagoDTO {
    private int idFormaPago;

    @NotEmpty(message = "El campo nombre es requerido")
    private String nombre;

}
