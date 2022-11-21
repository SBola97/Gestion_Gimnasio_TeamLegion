package com.GestionGimnasio.tesisgestiongimnasio.mappers;


import com.GestionGimnasio.tesisgestiongimnasio.dto.DisciplinasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Disciplinas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")

public interface DisciplinasMapper {
    @Mappings({
            @Mapping(source="idDisciplina",target = "idDisciplina"),
            @Mapping(source="nombre",target = "nombre"),
    })
    DisciplinasDTO toDisciplinasDTO(Disciplinas disciplinas);
    List<DisciplinasDTO> toDisciplinasDTO(List<Disciplinas> disciplinasList);

    @InheritInverseConfiguration
    Disciplinas toDisciplinas(DisciplinasDTO disciplinas);
}
