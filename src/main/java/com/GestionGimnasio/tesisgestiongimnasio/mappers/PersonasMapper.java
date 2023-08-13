package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonasMapper {
    @Mappings({
            @Mapping(source = "idPersona", target = "idPersona"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellidos", target = "apellidos"),
            @Mapping(source = "cedula", target = "cedula"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "edad", target = "edad"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "estatura", target = "estatura"),
            @Mapping(source = "roles.idRol", target = "idRol"),
            @Mapping(source = "cinturonbjj", target = "cinturonbjj"),
            @Mapping(source = "cinturonkb", target = "cinturonkb")
    })
    PersonasDTO toPersonasDTO(Personas personas);
    List<PersonasDTO> toPersonasDTO(List<Personas> personasList);
    @InheritInverseConfiguration
    Personas toPersonas (PersonasDTO personas);
    List<Personas> toPersonas (List<PersonasDTO> personasDTOList);


}
