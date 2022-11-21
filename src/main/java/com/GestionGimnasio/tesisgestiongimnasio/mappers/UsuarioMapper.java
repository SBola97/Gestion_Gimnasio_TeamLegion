package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses= {RolesMapper.class})
public interface UsuarioMapper {
    @Mappings({
        @Mapping(source="idUsuario", target="idUsuario"),
        @Mapping(source="nombreUsuario", target="nombreUsuario"),
        @Mapping(source="contraseña", target="contraseña"),
        @Mapping(source="personas.idPersona", target="idPersona")
    })
    UsuariosDTO toUsuariosDTO(Usuarios usuarios);
    List<UsuariosDTO> toUsuariosDTO(List<Usuarios> usuariosList);

    @InheritInverseConfiguration
    Usuarios toUsuarios(UsuariosDTO usuarios);

}
