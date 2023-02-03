package com.GestionGimnasio.tesisgestiongimnasio.mappers;


import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Pagos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses={FormasPagoMapper.class})
public interface PagosMapper {

    @Mappings({
            @Mapping(source="idPago", target= "idPago"),
            @Mapping(source="fechaPago", target= "fechaPago"),
            @Mapping(source="estadoPago", target= "estadoPago"),
            @Mapping(source="inscripciones.idInscripcion", target= "idInscripcion"),
            @Mapping(source="inscripciones.personas.nombre", target= "nombre"),
            @Mapping(source="inscripciones.personas.apellidos", target= "apellidos"),
            @Mapping(source="valorp", target= "valorp"),
            @Mapping(source="formaPago", target= "formaspago")
    })
    PagosDTO toPagosDTO(Pagos pagos);

    List<PagosDTO> toPagosDTO(List<Pagos> pagosList);

    
    @InheritInverseConfiguration
    Pagos toPagos(PagosDTO pagos);
    List<Pagos> toPagos(List<PagosDTO> pagosDTOList);

}
