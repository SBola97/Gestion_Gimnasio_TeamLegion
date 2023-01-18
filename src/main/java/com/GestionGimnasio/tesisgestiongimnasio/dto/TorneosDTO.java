package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TorneosDTO {

    private int idTorneo;

    private String nombre;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private String ciudad;


    private int idDisciplina;

    private String nombreDisciplina;
}
