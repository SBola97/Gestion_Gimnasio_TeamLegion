package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import org.springframework.data.domain.Page;

import java.util.List;
public interface iInscripcionesService {

    InscripcionesDTO ingresarInscripcion(InscripcionesDTO inscripcionesDTO);

    InscripcionesDTO modificarInscripcion(int idInscripcion, InscripcionesDTO inscripcionesDTO);

    InscripcionesDTO buscarInscripcion(int idInscripcion);

    Inscripciones findInscripcion(int idInscripcion);

    void eliminarInscripcion(int idInscripcion);

    List<InscripcionesDTO> obtenerInscripcion();

    Page<Inscripciones> obtenerInscripciones(int pageNumber);

    Page<Inscripciones> obtenerInscripcionesSort(String campo, String direccion, int pageNumber);

    void verificarInscripcionesVencidas();

    List<InscripcionesDTO> obtenerInscripcionesPorVencer();

    List<InscripcionesDTO> obtenerInscripcionesSinPago();
}
