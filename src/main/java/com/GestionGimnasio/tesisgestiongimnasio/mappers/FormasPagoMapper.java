package com.GestionGimnasio.tesisgestiongimnasio.mappers;


import com.GestionGimnasio.tesisgestiongimnasio.dto.FormaPagoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.FormaPago;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormasPagoMapper {
    @Mappings({
            @Mapping(source="idFormaPago",target="idFormaPago"),
            @Mapping(source="nombre",target="nombre")
    })
    FormaPagoDTO toFormaPagoDTO(FormaPago formaPago);

    List<FormaPagoDTO> toFormaPagoDTO(List<FormaPago> formaPagoList);

    @InheritInverseConfiguration

    FormaPago toFormaPago(FormaPagoDTO formaPago);


}
