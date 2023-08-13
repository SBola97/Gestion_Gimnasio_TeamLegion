package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ClienteDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ProfesorDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface iPersonasService {


    @Transactional
    PersonasDTO registrarPersona(PersonasDTO personasDTO);

    @Transactional
    ProfesorDTO registrarProfesor(ProfesorDTO personasDTO);

    PersonasDTO modificarPersona(int idPersona, PersonasDTO personasDTO);
    void eliminarPersona(int idPersona);
    PersonasDTO buscarPersona(int idPersona);

    ProfesorDTO buscarProfesor(int idPersona);

    List<PersonasDTO> obtenerPersona();

    List<Personas> obtenerPersonas();

    //List<ClienteDTO> obtenerClientes();

    //List<ProfesorDTO> obtenerProfesores();


    Page<Personas> obtenerClientes(int pageNumber);

    Page<Personas> obtenerProfesores(int pageNumber);

    Page<Personas> obtenerSuscriptores(int pageNumber);

    Page<Personas> searchPersonas(String nombre, int pageNumber);

    Page<Personas> searchClientes(String searchTerm, int pageNumber);

    Page<Personas> searchProfesores(String searchTerm, int pageNumber);

    List<PersonasDTO> obtenerPersonasSinSuscripcion();

    List<PersonasDTO> obtenerPersonasSinUser();

    //Set<Competidores_Torneo> createListaCompetidores(Personas personas);
}
