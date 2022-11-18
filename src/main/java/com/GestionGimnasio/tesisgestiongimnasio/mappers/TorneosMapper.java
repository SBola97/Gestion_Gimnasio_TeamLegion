package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.TorneosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Torneos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TorneosMapper {

    @Mappings({
            @Mapping(source = "idTorneo",target = "idTorneo" ),
            @Mapping(source = "nombre",target = "nombre" ),
            @Mapping(source = "fecha",target = "fecha" ),
            @Mapping(source = "ciudad",target = "ciudad" ),
            @Mapping(source = "disciplinas.idDisciplina",target = "idDisciplina" ),
    })
    TorneosDTO toTorneosDTO(Torneos torneos);
    List<TorneosDTO>toTorneosDTO(List<Torneos> torneosList);

    @InheritInverseConfiguration
    Torneos toTorneos(TorneosDTO torneos);
}
