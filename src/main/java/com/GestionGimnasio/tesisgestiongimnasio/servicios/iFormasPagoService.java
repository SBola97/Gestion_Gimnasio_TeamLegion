package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.FormaPagoDTO;

import java.util.List;
public interface iFormasPagoService {

    FormaPagoDTO ingresarFormaPago(FormaPagoDTO formaPagoDTO);

    FormaPagoDTO modificarFormaPago(int idFormaPago, FormaPagoDTO formaPagoDTO);

    void eliminarFormaPago(int idFormaPago);

    FormaPagoDTO buscarFormaPago(int idFormaPago);

    List<FormaPagoDTO>obtenerFormasPago();


}
