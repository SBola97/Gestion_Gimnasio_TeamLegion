package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.InscripcionMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.InscripcionesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.ModalidadesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        //int idMod = inscripcionesDTO.getIdModalidad();
        int idPer = inscripcionesDTO.getIdPersona();


        LocalDate fechaActual = LocalDate.now();

        if(inscripcionesDTO.getIdInscripcion() == 0) {
            if(inscripcionesRepository.findInscripcionesByPersonas_IdPersona(idPer).isPresent())
            {
                throw new RuntimeException("Esta persona ya cuenta con una inscripción registrada");
            }
            if (inscripcionesDTO.getFechaInicio().isBefore(fechaActual) || inscripcionesDTO.getFechaFin().isBefore(fechaActual)) {
                throw new RuntimeException("Fecha no válida para inscripción");
            }
        }
        Inscripciones inscripciones = mapper.toInscripciones(inscripcionesDTO);

        /*Modalidades mod = modalidadesRepository.findById(idMod)
                .orElseThrow(()-> new RuntimeException("Modalidad no encontrada"));
        inscripciones.setModalidades(mod);*/

        /*Personas per = personasRepository.findById(idPer)
                .orElseThrow(()-> new RuntimeException("Persona no encontrada"));
        inscripciones.setPersonas(per);*/


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
                .orElseThrow(()-> new RuntimeException("Inscripción no encontrada")));
    }

    @Override
    public void eliminarInscripcion(int idInscripcion) {
        inscripcionesRepository.deleteById(idInscripcion);
    }

    @Override
    public List<InscripcionesDTO> obtenerInscripcion() {
        return mapper.toInscripcionesDTO((List<Inscripciones>)inscripcionesRepository.findAll());
    }


    @Override
    public Page<Inscripciones> obtenerInscripciones(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber -1,5);
        Page<Inscripciones> inscripciones = inscripcionesRepository.findAll(pageable);
        return inscripciones;
    }

    @Override
    public void verificarInscripcionesVencidas()
    {
        List<Inscripciones> listI = inscripcionesRepository.findAll();
        LocalDate date = LocalDate.now();
        for(Inscripciones i : listI)
        {
           if(i.getFechaFin().isEqual(date)||i.getFechaFin().isBefore(date))
           {
               i.setEstado("Vencida");
           }
           else {
               i.setEstado("Activa");
           }
        }
    }

    @Override
    public List<InscripcionesDTO> obtenerInscripcionesPorVencer()
    {
        return mapper.toInscripcionesDTO((List<Inscripciones>)inscripcionesRepository.findInscripcionesByFechaFin());
    }

    @Override
    public List<InscripcionesDTO> obtenerInscripcionesSinPago()
    {
        return  mapper.toInscripcionesDTO((List<Inscripciones>)inscripcionesRepository.findInscripcionesNoPagadas());
    }
}
