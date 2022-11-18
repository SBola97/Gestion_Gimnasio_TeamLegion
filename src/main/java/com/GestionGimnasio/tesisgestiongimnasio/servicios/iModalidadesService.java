package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.ModalidadesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Modalidades;

import java.util.List;

public interface iModalidadesService {

    ModalidadesDTO ingresarModalidad(ModalidadesDTO modalidadesDTO);

    ModalidadesDTO modificarModalidad(int idModalidad, ModalidadesDTO modalidadesDTO);

    ModalidadesDTO buscarModalidad(int idModalidad);

    void eliminarModalidad(int idModalidad);

    List<ModalidadesDTO> obtenerModalidades();
}
