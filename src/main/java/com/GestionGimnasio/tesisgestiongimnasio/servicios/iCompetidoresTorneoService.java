package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.CompetidoresTorneoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.FichaDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;

import java.util.List;

public interface iCompetidoresTorneoService {

    CompetidoresTorneoDTO ingresarCompetidorTorneo(CompetidoresTorneoDTO competidoresTorneoDTO);

    void ingresarCompetidores(Competidores_Torneo competidoresTorneo);

    CompetidoresTorneoDTO modificarCompetidorTorneo(int idCompetidorT, CompetidoresTorneoDTO competidoresTorneoDTO);

    CompetidoresTorneoDTO buscarCompetidorTorneo(int idCompetidorT);

    void eliminarCompetidorTorneo(int idCompetidorT);

    List<CompetidoresTorneoDTO> obtenerCompetidoresTorneo();

    List<FichaDTO> obtenerFichaCompetidor(int idP);


}
