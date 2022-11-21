package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ProfesorDTO;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.Personas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesorMapper {

    @Mappings({
            @Mapping(source = "idPersona", target = "idProfesor"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellidos", target = "apellidos"),
            @Mapping(source = "cedula", target = "cedula"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "edad", target = "edad"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "estatura", target = "estatura")
    })
    ProfesorDTO toprofesorDTO (Personas personas);
    List<ProfesorDTO> toprofesorDTO(List<Personas> personasList);

    @InheritInverseConfiguration
    Personas toPersonas (ProfesorDTO profesor);
}
