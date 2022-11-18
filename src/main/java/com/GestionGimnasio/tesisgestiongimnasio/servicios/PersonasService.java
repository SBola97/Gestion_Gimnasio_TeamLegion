package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.PersonasMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonasService implements iPersonasService {

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PersonasMapper mapper;

    @Override
    public PersonasDTO ingresarPersona(PersonasDTO personasDTO)
    {

        int idRol = personasDTO.getIdRol();
        Personas personas = mapper.toPersonas(personasDTO);

        Roles rolesR = rolesRepository.findById(idRol).orElseThrow(()-> new RuntimeException("No encontrado"));

        personas.setRoles(rolesR);

        personasRepository.save(personas);
        return mapper.toPersonasDTO(personas);
    }

    @Override
    public PersonasDTO modificarPersona(int idPersona, PersonasDTO personasDTO) {
        return null;
    }

    @Override
    public void eliminarPersona(int idPersona) {
        personasRepository.deleteById(idPersona);
    }

    @Override
    public PersonasDTO buscarPersona(int idPersona) {
        return mapper.toPersonasDTO(personasRepository.findById(idPersona)
                .orElseThrow(()-> new RuntimeException("Persona no encontrada")));
    }

    @Override
    public List<PersonasDTO> obtenerPersona() {
        return mapper.toPersonasDTO((List<Personas>)personasRepository.findAll());
    }

}
