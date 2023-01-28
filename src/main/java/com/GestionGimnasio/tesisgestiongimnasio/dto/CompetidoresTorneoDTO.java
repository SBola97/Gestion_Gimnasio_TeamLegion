package com.GestionGimnasio.tesisgestiongimnasio.dto;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo_Key;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Torneos;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class CompetidoresTorneoDTO {

    private int idCompetidorTorneo;

    private int idPersona;

    private int idTorneo;

    private String nombret;

    private String ciudadt;

    private String nombrep;

    private String apellidos;

    private float pesoc;

    private String categoriaPeso;


}
