package com.GestionGimnasio.tesisgestiongimnasio.dto;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Pagos;
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

    @NotNull
    @NotEmpty(message = "El campo estadoPago es requerido")
    @NotBlank(message = "El campo estadoPago es requerido")
    private String estadoPago;

    private int idInscripcion;

    private String nombre;

    private String apellidos;

    private float valorp; //valor a pagar

    private FormaPagoDTO formaspago;

    private float valori; //valor de inscripción inicial

    private String oferta;
}
