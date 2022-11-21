package com.GestionGimnasio.tesisgestiongimnasio.mappers;


import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Pagos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagosMapper {

    @Mappings({
            @Mapping(source="idPago", target= "idPago"),
            @Mapping(source="fechaPago", target= "fechaPago"),
            @Mapping(source="estadoPago", target= "estadoPago"),
            @Mapping(source="inscripciones.idInscripcion", target= "idInscripcion"),
            @Mapping(source="formaPago.idFormaPago", target= "idFormaPago")
    })
    PagosDTO toPagosDTO(Pagos pagos);

    List<PagosDTO> toPagosDTO(List<Pagos> pagosList);

    @InheritInverseConfiguration
    Pagos toPagos(PagosDTO pagos);
}
