package com.GestionGimnasio.tesisgestiongimnasio.mappers;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ModalidadesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.Modalidades;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModalidadesMapper {

    @Mappings({
            @Mapping(source="idModalidad",target="idModalidad"),
            @Mapping(source="tipo",target="tipo"),
            @Mapping(source="nombre",target="nombre"),
            @Mapping(source="valor",target="valor")
    })
    ModalidadesDTO toModalidadesDTO(Modalidades modalidades);

    List<ModalidadesDTO> toModalidadesDTO(List<Modalidades> modalidadesList);

    @InheritInverseConfiguration

    Modalidades toModalidades(ModalidadesDTO modalidades);
}
