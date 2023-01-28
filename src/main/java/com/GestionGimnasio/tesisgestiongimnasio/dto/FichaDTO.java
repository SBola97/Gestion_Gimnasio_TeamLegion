package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FichaDTO {

    private String nombret;

    private String ciudadt;

    private LocalDate fechat;

    private String nombrep;

    private String apellidos;

    private float pesoc;

    private String categoriaPeso;

    private String nombreD;
}
