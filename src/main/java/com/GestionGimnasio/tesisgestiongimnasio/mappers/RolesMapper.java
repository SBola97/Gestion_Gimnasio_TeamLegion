package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.RolesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    @Mappings({
        @Mapping(source="idRol",target = "idRol"),
        @Mapping(source="nombre",target = "nombre"),
    })
    RolesDTO toRolesDTO(Roles roles);
    List<RolesDTO> toRolesDTO(List<Roles> rolesList);

    @InheritInverseConfiguration
    Roles toRoles(RolesDTO roles);
}
