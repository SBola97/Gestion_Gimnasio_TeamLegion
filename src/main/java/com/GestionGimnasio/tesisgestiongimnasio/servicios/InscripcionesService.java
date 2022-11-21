package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Modalidades;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.InscripcionMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.InscripcionesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.ModalidadesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionesService implements iInscripcionesService{

    @Autowired
    private InscripcionesRepository inscripcionesRepository;

    @Autowired
    private ModalidadesRepository modalidadesRepository;

    @Autowired
    private PersonasRepository personasRepository;


    @Autowired
    private InscripcionMapper mapper;


    @Override
    public InscripcionesDTO ingresarInscripcion(InscripcionesDTO inscripcionesDTO) {
        int idMod = inscripcionesDTO.getIdModalidad();
        int idPer = inscripcionesDTO.getIdPersona();

        LocalDate fechaActual = LocalDate.now();

        if(inscripcionesDTO.getFechaInicio().isBefore(fechaActual)){
            throw new RuntimeException("Fecha no habilitada para inscripción, ingrese una fecha válida, por favor");
        }

        Inscripciones inscripciones = mapper.toInscripciones(inscripcionesDTO);

        Modalidades mod = modalidadesRepository.findById(idMod)
                .orElseThrow(()-> new RuntimeException("Modalidad no encontrada"));
        inscripciones.setModalidades(mod);

        Personas per = personasRepository.findById(idPer)
                .orElseThrow(()-> new RuntimeException("Persona no encontrada"));
        inscripciones.setPersonas(per);


        inscripcionesRepository.save(inscripciones);
        return mapper.toInscripcionesDTO(inscripciones);
    }

    @Override
    public InscripcionesDTO modificarInscripcion(int idInscripcion, InscripcionesDTO inscripcionesDTO) {
        return null;
    }

    @Override
    public InscripcionesDTO buscarInscripcion(int idInscripcion) {
        return mapper.toInscripcionesDTO(inscripcionesRepository.findById(idInscripcion)
                .orElseThrow(()-> new RuntimeException("Inscripcion no encontrada")));
    }

    @Override
    public void eliminarInscripcion(int idInscripcion) {
        inscripcionesRepository.deleteById(idInscripcion);
    }

    @Override
    public List<InscripcionesDTO> obtenerInscripcion() {
        return mapper.toInscripcionesDTO((List<Inscripciones>)inscripcionesRepository.findAll());
    }
}
