package com.GestionGimnasio.tesisgestiongimnasio.servicios;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.FormaPago;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Pagos;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.PagosMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.FormaPagoRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.InscripcionesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagosService implements iPagosService {

    @Autowired
    private PagosRepository pagosRepository;

    @Autowired
    private FormaPagoRepository formaPagoRepository;

    @Autowired
    private InscripcionesRepository inscripcionesRepository;

    @Autowired
    private PagosMapper mapper;

    @Override
    public PagosDTO ingresarPago(PagosDTO pagosDTO) {

        int idFormaP = pagosDTO.getIdFormaPago();
        int idInsc = pagosDTO.getIdInscripcion();

        Pagos pagos = mapper.toPagos(pagosDTO);

        Inscripciones insc = inscripcionesRepository.findById(idInsc)
                .orElseThrow(()-> new RuntimeException("Inscripcion no encontrada"));
        pagos.setInscripciones(insc);

        FormaPago fp = formaPagoRepository.findById(idFormaP)
                .orElseThrow(()-> new RuntimeException("Forma de Pago no encontrada"));
        pagos.setFormaPago(fp);



        pagosRepository.save(pagos);
        return mapper.toPagosDTO(pagos);
    }

    @Override
    public PagosDTO modificarPago(int idPago, PagosDTO pagosDTO) {
        return null;
    }

    @Override
    public void eliminarPago(int idPago) {
        pagosRepository.deleteById(idPago);
    }

    @Override
    public PagosDTO buscarPago(int idPago) {
        return mapper.toPagosDTO(pagosRepository.findById(idPago)
                .orElseThrow(()-> new RuntimeException("Pago no encontrado")));
    }

    @Override
    public List<PagosDTO> obtenerPagos() {
        return mapper.toPagosDTO((List<Pagos>)pagosRepository.findAll());
    }
}
