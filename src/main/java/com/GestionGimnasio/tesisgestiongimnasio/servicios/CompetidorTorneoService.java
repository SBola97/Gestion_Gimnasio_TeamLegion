package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.CompetidoresTorneoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.FichaDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo_Key;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Torneos;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.CompetidoresTorneoMapper;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.FichaMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.Competidores_TorneoRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.TorneosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompetidorTorneoService implements iCompetidoresTorneoService{

    @Autowired
    private Competidores_TorneoRepository competidores_torneoRepository;

    @Autowired
    private TorneosRepository torneosRepository;

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private CompetidoresTorneoMapper mapper;

    @Autowired
    private FichaMapper mapperc;

    @Override
    public CompetidoresTorneoDTO ingresarCompetidorTorneo(CompetidoresTorneoDTO competidoresTorneoDTO) {


        Competidores_Torneo competidores_torneo = mapper.toCompetidoresTorneo(competidoresTorneoDTO);

/*        competidores_torneo.setId(new Competidores_Torneo_Key
                (competidoresTorneoDTO.getIdTorneo(),competidoresTorneoDTO.getIdPersona()));*/

        competidores_torneoRepository.save(competidores_torneo);

        return mapper.toCompetidoresTorneoDTO(competidores_torneo);
    }

    @Override
    public List<FichaDTO> obtenerFichaCompetidor(int idP) {
       return mapperc.toFichaDTO(competidores_torneoRepository.findCompetidores_TorneoByPersonasIdPersona(idP));
    }

    @Override
    public void ingresarCompetidores(Competidores_Torneo competidores_torneo) {

/*        Competidores_Torneo_Key key = new Competidores_Torneo_Key();
        key.setIdPersona(competidores_torneo.getIdPersona());
        key.setIdTorneo(competidores_torneo.getIdTorneo());
        competidores_torneo.setId(key);*/

        competidores_torneoRepository.save(competidores_torneo);
    }

    @Override
    public CompetidoresTorneoDTO modificarCompetidorTorneo
            (int idCompetidorT, CompetidoresTorneoDTO competidoresTorneoDTO) {
        return null;
    }

    @Override
    @ResponseBody
    public CompetidoresTorneoDTO buscarCompetidorTorneo(int idCt) {
        return mapper.toCompetidoresTorneoDTO(competidores_torneoRepository.findById(idCt)
                .orElseThrow(()-> new RuntimeException("Competidor no encontrado")));
    }

    @Override
    public void eliminarCompetidorTorneo(int idCompetidorT) {
        competidores_torneoRepository.deleteById(idCompetidorT);
    }

    @Override
    public List<CompetidoresTorneoDTO> obtenerCompetidoresTorneo() {
        return mapper.toCompetidoresTorneoDTO((List<Competidores_Torneo>)competidores_torneoRepository.findAll());
    }
}
