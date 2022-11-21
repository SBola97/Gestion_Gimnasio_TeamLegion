package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.CompetidoresTorneoDTO;

import java.util.List;

public interface iCompetidoresTorneoService {

    CompetidoresTorneoDTO ingresarCompetidorTorneo(CompetidoresTorneoDTO competidoresTorneoDTO);

    CompetidoresTorneoDTO modificarCompetidorTorneo(int idCompetidorT, CompetidoresTorneoDTO competidoresTorneoDTO);

    CompetidoresTorneoDTO buscarCompetidorTorneo(int idCompetidorT);

    void eliminarCompetidorTorneo(int idCompetidorT);

    List<CompetidoresTorneoDTO> obtenerCompetidoresTorneo();
}
