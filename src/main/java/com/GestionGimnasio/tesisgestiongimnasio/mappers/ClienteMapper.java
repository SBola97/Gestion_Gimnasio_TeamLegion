package com.GestionGimnasio.tesisgestiongimnasio.mappers;


import com.GestionGimnasio.tesisgestiongimnasio.dto.ClienteDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mappings({
            @Mapping(source = "idPersona", target = "idCliente"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellidos", target = "apellidos"),
            @Mapping(source = "cedula", target = "cedula"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "edad", target = "edad"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "estatura", target = "estatura"),
            @Mapping(source = "cinturonbjj", target = "cinturonbjj"),
            @Mapping(source = "cinturonkb", target = "cinturonkb")
    })
    ClienteDTO toclienteDTO(Personas personas);
    List<ClienteDTO> toclienteDTO(List<Personas> personasList);

    @InheritInverseConfiguration
    Personas toPersonas(ClienteDTO cliente);


}
