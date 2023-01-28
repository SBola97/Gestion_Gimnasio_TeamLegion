package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class ReporteDeudoresDTO {

    private int mes;

    private float valorp;

    private String nombrePersona;

    private String apellidos;

    private Date fechaInicio;

    private Date fechaFin;
}
