package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ModalidadesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Disciplinas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Modalidades;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.ModalidadesMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.ModalidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalidadesService implements iModalidadesService {

    @Autowired
    private ModalidadesRepository modalidadesRepository;

    @Autowired
    private ModalidadesMapper mapper;


    @Override
    public ModalidadesDTO ingresarModalidad(ModalidadesDTO modalidadesDTO) {
        Modalidades modalidades = mapper.toModalidades(modalidadesDTO);
        /*modalidades.setIdModalidad(modalidadesDTO.getIdModalidad());
        modalidades.setTipo(modalidadesDTO.getTipo());
        modalidades.setNombre(modalidadesDTO.getNombre());
        modalidades.setValor(modalidadesDTO.getValor());*/
        modalidadesRepository.save(modalidades);

        return mapper.toModalidadesDTO(modalidades);

    }

    @Override
    public ModalidadesDTO modificarModalidad(int idModalidad, ModalidadesDTO modalidadesDTO) {
        return null;
    }

    @Override
    public ModalidadesDTO buscarModalidad(int idModalidad) {
        return null;
    }

    @Override
    public void eliminarModalidad(int idModalidad) {
        modalidadesRepository.deleteById(idModalidad);
    }

    @Override
    public List<ModalidadesDTO> obtenerModalidades() {
        return mapper.toModalidadesDTO((List<Modalidades>)modalidadesRepository.findAll());
    }
}
