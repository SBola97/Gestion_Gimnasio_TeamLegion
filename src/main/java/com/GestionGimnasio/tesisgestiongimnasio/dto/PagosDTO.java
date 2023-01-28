package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPago;

    @NotEmpty(message = "El campo estadoPago es requerido")
    @NotBlank(message = "El campo estadoPago es requerido")
    private String estadoPago;

    private int idInscripcion;

    private String nombre;

    private String apellidos;

    private float valorp;

    private FormaPagoDTO formaspago;

}
