package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.CompetidoresTorneoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.FichaDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
@Mapper(componentModel = "spring")
public interface FichaMapper {
    @Mappings({
            @Mapping(source="torneos.nombre",target = "nombret"),
            @Mapping(source="torneos.fecha",target = "fechat"),
            @Mapping(source="torneos.ciudad",target = "ciudadt"),
            @Mapping(source="personas.nombre",target = "nombrep"),
            @Mapping(source="personas.apellidos",target = "apellidos"),
            @Mapping(source="pesoc",target = "pesoc"),
            @Mapping(source="categoriaPeso",target = "categoriaPeso"),
            @Mapping(source="torneos.disciplinas.nombre",target = "nombreD")
    })
    FichaDTO toFichaDTO(Competidores_Torneo competidores_torneo);

    List<FichaDTO> toFichaDTO(List<Competidores_Torneo> competidores_torneoList);

    @InheritInverseConfiguration
    Competidores_Torneo toCompetidoresTorneo(FichaDTO fichaDTO);
}
