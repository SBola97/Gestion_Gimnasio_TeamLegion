package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.RolesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.Roles;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.RolesMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService implements iRolesService{

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private RolesMapper mapper;


    @Override
    public RolesDTO ingresarRol(RolesDTO rolesDTO) {
        Roles roles = mapper.toRoles(rolesDTO);

        /*
        roles.setIdRol(rolesDTO.getIdRol());
        roles.setNombre(rolesDTO.getNombre());*/

        rolesRepository.save(roles);
        return mapper.toRolesDTO(roles);
    }

    @Override
    public RolesDTO modificarRol(int idRol, RolesDTO rolesDTO) {
        return null;
    }

    @Override
    public void eliminarRol(int idRol) {
        rolesRepository.deleteById(idRol);
    }

    @Override
    public RolesDTO buscarRol(int idRol) {
        return null;
    }

    @Override
    public List<RolesDTO> obtenerRoles() {
        return mapper.toRolesDTO((List<Roles>)rolesRepository.findAll());
    }
}
