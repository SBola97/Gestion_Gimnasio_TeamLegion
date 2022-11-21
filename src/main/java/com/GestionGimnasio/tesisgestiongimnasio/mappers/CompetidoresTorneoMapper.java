package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.CompetidoresTorneoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.Competidores_Torneo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetidoresTorneoMapper {
    @Mappings({
            @Mapping(source="idCompetidorTorneo",target = "idCompetidorTorneo"),
            @Mapping(source="personas.idPersona",target = "idPersona"),
            @Mapping(source="torneos.idTorneo",target = "idTorneo")
    })
    CompetidoresTorneoDTO toCompetidoresTorneoDTO(Competidores_Torneo competidores_torneo);
    List<CompetidoresTorneoDTO>toCompetidoresTorneoDTO(List<Competidores_Torneo> competidores_torneoList);

    @InheritInverseConfiguration
    Competidores_Torneo toCompetidoresTorneo(CompetidoresTorneoDTO competidoresTorneo);
}
