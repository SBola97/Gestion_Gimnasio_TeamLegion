package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.TorneosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.Disciplinas;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.Torneos;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.TorneosMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.DisciplinasRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.TorneosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorneosService implements iTorneosService {

    @Autowired
    private TorneosRepository torneosRepository;

    @Autowired
    private DisciplinasRepository disciplinasRepository;

    @Autowired
    private TorneosMapper mapper;

    @Override
    public TorneosDTO ingresarTorneo(TorneosDTO torneosDTO) {
        int idDisc = torneosDTO.getIdDisciplina();
        Torneos torneos = mapper.toTorneos(torneosDTO);

        Disciplinas disciplinas = disciplinasRepository.findById(idDisc)
                .orElseThrow(()-> new RuntimeException("No encontrado"));
        torneos.setDisciplinas(disciplinas);

        torneosRepository.save(torneos);
        return mapper.toTorneosDTO(torneos);
    }

    @Override
    public TorneosDTO modificarTorneo(TorneosDTO torneosDTO) {
        return null;
    }

    @Override
    public void eliminarTorneo(int idTorneo) {
        torneosRepository.deleteById(idTorneo);
    }

    @Override
    public TorneosDTO buscarTorneo(int idTorneo) {
        return null;
    }

    @Override
    public List<TorneosDTO> obtenerTorneos() {
        return mapper.toTorneosDTO((List<Torneos>)torneosRepository.findAll());
    }
}
