package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class ReporteGananciasDTO {

    private int mes;

    private BigDecimal total;

    private String nombrePersona;

    private String apellidos;

    private Date fecha;

    private float valorp;
}
