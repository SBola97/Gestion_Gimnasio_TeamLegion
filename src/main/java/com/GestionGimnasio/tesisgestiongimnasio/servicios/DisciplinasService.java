package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.DisciplinasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Disciplinas;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.DisciplinasMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.DisciplinasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinasService implements iDisciplinasService {

    @Autowired
    private DisciplinasRepository disciplinasRepository;

    @Autowired
    private DisciplinasMapper mapper;


    @Override
    public DisciplinasDTO ingresarDisciplina(DisciplinasDTO disciplinasDTO) {
        Disciplinas disciplinas = mapper.toDisciplinas(disciplinasDTO);

        disciplinas.setIdDisciplina(disciplinasDTO.getIdDisciplina());
        disciplinas.setNombre(disciplinasDTO.getNombre());

        disciplinasRepository.save(disciplinas);
        return mapper.toDisciplinasDTO(disciplinas);
    }

    @Override
    public DisciplinasDTO modificarDisciplina(DisciplinasDTO disciplinasDTO) {
        return null;
    }

    @Override
    public void eliminarDisciplina(int idDisciplina) {
        disciplinasRepository.deleteById(idDisciplina);
    }

    @Override
    public DisciplinasDTO buscarDisciplina(int idDisciplina) {
        return null;
    }

    @Override
    public List<DisciplinasDTO> obtenerDisciplinas() {
        return mapper.toDisciplinasDTO((List<Disciplinas>)disciplinasRepository.findAll());
    }
}
