package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.CompetidoresTorneoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Torneos;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.CompetidoresTorneoMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.Competidores_TorneoRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.TorneosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public CompetidoresTorneoDTO ingresarCompetidorTorneo(CompetidoresTorneoDTO competidoresTorneoDTO) {

        int idTorneo = competidoresTorneoDTO.getIdTorneo();
        int idPersona = competidoresTorneoDTO.getIdPersona();

        Competidores_Torneo competidores_torneo = mapper.toCompetidoresTorneo(competidoresTorneoDTO);

        Torneos idTorn = torneosRepository.findById(idTorneo).
                orElseThrow(()-> new RuntimeException("Torneo no encontrado"));
        competidores_torneo.setTorneos(idTorn);

        Personas idPers = personasRepository.findById(idPersona).
                orElseThrow(()-> new RuntimeException("Persona no encontrada"));
        competidores_torneo.setPersonas(idPers);

        competidores_torneoRepository.save(competidores_torneo);

        return mapper.toCompetidoresTorneoDTO(competidores_torneo);
    }

    @Override
    public CompetidoresTorneoDTO modificarCompetidorTorneo
            (int idCompetidorT, CompetidoresTorneoDTO competidoresTorneoDTO) {
        return null;
    }

    @Override
    public CompetidoresTorneoDTO buscarCompetidorTorneo(int idCompetidorT) {
        return mapper.toCompetidoresTorneoDTO(competidores_torneoRepository.findById(idCompetidorT)
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
