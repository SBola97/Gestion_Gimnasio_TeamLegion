package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;

import java.util.List;
public interface iPagosService {

    PagosDTO ingresarPago(PagosDTO pagosDTO);

    PagosDTO modificarPago(int idPago, PagosDTO pagosDTO);

    void eliminarPago(int idPago);

    PagosDTO buscarPago(int idPago);

    List<PagosDTO> obtenerPagos();
}
