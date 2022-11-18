package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.RolesDTO;

import java.util.List;

public interface iRolesService {

    RolesDTO ingresarRol(RolesDTO rolesDTO);

    RolesDTO modificarRol(int idRol, RolesDTO rolesDTO);

    void eliminarRol(int idRol);

    RolesDTO buscarRol(int idRol);

    List<RolesDTO> obtenerRoles();
}
