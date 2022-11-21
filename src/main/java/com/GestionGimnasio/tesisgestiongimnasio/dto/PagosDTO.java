package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PagosDTO {

    private int idPago;

    @NotNull(message = "El campo fechaPago es requerido")
    private LocalDate fechaPago;

    @NotEmpty(message = "El campo estadoPago es requerido")
    @NotBlank(message = "El campo estadoPago es requerido")
    private String estadoPago;

    @NotNull(message = "El campo idInscripcion es requerido")
    private int idInscripcion;

    @NotNull(message = "El campo idFormaPago es requerido")
    private int idFormaPago;

}
