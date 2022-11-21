package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.FormaPagoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.FormaPago;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.FormasPagoMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.FormaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormasPagoService implements iFormasPagoService{

    @Autowired
    private FormaPagoRepository formaPagoRepository;

    @Autowired
    private FormasPagoMapper mapper;

    @Override
    public FormaPagoDTO ingresarFormaPago(FormaPagoDTO formaPagoDTO) {
        FormaPago formaPago = mapper.toFormaPago(formaPagoDTO);

        formaPagoRepository.save(formaPago);
        return mapper.toFormaPagoDTO(formaPago);
    }

    @Override
    public FormaPagoDTO modificarFormaPago(int idFormaPago, FormaPagoDTO formaPagoDTO) {
        return null;
    }

    @Override
    public void eliminarFormaPago(int idFormaPago) {
        formaPagoRepository.deleteById(idFormaPago);
    }

    @Override
    public FormaPagoDTO buscarFormaPago(int idFormaPago) {
        Optional<FormaPago> formaPagoRes = formaPagoRepository.findById(idFormaPago);
        return mapper.toFormaPagoDTO(formaPagoRes
                .orElseThrow(()-> new RuntimeException("Forma de Pago no encontrada")));
    }

    @Override
    public List<FormaPagoDTO> obtenerFormasPago() {
        return mapper.toFormaPagoDTO((List<FormaPago>)formaPagoRepository.findAll());
    }
}
