package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ClienteDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ProfesorDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;

import java.util.List;
import java.util.Set;

public interface iPersonasService {

    PersonasDTO ingresarPersona(PersonasDTO personasDTO);

    void registrarPersona(Personas personas);
    PersonasDTO modificarPersona(int idPersona, PersonasDTO personasDTO);
    void eliminarPersona(int idPersona);
    PersonasDTO buscarPersona(int idPersona);
    List<PersonasDTO> obtenerPersona();

    List<Personas> obtenerPersonas();

    List<ClienteDTO> obtenerClientes();

    List<ProfesorDTO> obtenerProfesores();

    //Set<Competidores_Torneo> createListaCompetidores(Personas personas);
}
