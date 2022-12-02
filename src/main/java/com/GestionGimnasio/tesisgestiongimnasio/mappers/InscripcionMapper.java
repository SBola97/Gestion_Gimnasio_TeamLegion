package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses ={ModalidadesMapper.class} )
public interface InscripcionMapper {
    @Mappings({
            @Mapping(source="idInscripcion", target="idInscripcion"),
            @Mapping(source="fechaInicio", target="fechaInicio"),
            @Mapping(source="fechaFin", target="fechaFin"),
            @Mapping(source="estado", target="estado"),
            @Mapping(source="modalidades", target="modalidades"),
            @Mapping(source="personas.idPersona", target="idPersona"),
            @Mapping(source="personas.nombre", target="nombre"),
            @Mapping(source="personas.apellidos", target="apellidos")
    })
    InscripcionesDTO toInscripcionesDTO(Inscripciones inscripciones);
    List<InscripcionesDTO> toInscripcionesDTO(List<Inscripciones> inscripcionesList);

    @InheritInverseConfiguration
    Inscripciones toInscripciones(InscripcionesDTO inscripcionesDTO);
    List<Inscripciones> toInscripciones(List<InscripcionesDTO> inscripcionesDTOList);
}
