package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Pagos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
public interface iPagosService {

    PagosDTO ingresarPago(PagosDTO pagosDTO);

    PagosDTO modificarPago(int idPago, PagosDTO pagosDTO);

    void eliminarPago(int idPago);

    PagosDTO buscarPago(int idPago);

    List<PagosDTO> obtenerPagos();

    Page<Pagos> findPagos(int pageNumber);

    Page<Pagos> searchPagos(String keyword, int pageNumber);

    Page<Pagos> obtenerPagosSort(String campo, String direccion, int pageNumber);
}
