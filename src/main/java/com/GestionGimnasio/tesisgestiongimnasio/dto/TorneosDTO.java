package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TorneosDTO {

    private int idTorneo;

    private String nombre;

    private LocalDate fecha;

    private String ciudad;

    private int idDisciplina;
}
