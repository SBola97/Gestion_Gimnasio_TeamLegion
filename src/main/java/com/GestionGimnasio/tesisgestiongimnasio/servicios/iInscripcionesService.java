package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;

import java.util.List;
public interface iInscripcionesService {

    InscripcionesDTO ingresarInscripcion(InscripcionesDTO inscripcionesDTO);

    InscripcionesDTO modificarInscripcion(int idInscripcion, InscripcionesDTO inscripcionesDTO);

    InscripcionesDTO buscarInscripcion(int idInscripcion);

    void eliminarInscripcion(int idInscripcion);

    List<InscripcionesDTO> obtenerInscripcion();

}
