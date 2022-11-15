package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;

import java.util.List;

public interface iPersonasService {

    PersonasDTO ingresarPersona(PersonasDTO personasDTO);
    PersonasDTO modificarPersona(PersonasDTO personasDTO);
    void eliminarPersona(int idPersona);
    PersonasDTO buscarPersona(PersonasDTO personasDTO);
    List<PersonasDTO> obtenerPersona();
}
