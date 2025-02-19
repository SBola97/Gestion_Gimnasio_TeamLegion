package com.GestionGimnasio.tesisgestiongimnasio.servicios;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Pagos;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.PagosMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.FormaPagoRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.InscripcionesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        //int idFormaP = pagosDTO.getIdFormaPago();
        int idInsc = pagosDTO.getIdInscripcion();

        Pagos pagos = mapper.toPagos(pagosDTO);

/*
        Inscripciones insc = inscripcionesRepository.findById(idInsc)
                .orElseThrow(()-> new RuntimeException("Inscripcion no encontrada"));
        pagos.setInscripciones(insc);

        FormaPago fp = formaPagoRepository.findById(idFormaP)
                .orElseThrow(()-> new RuntimeException("Forma de Pago no encontrada"));
        pagos.setFormaPago(fp);

*/
        Inscripciones insc = inscripcionesRepository.findById(idInsc)
                .orElseThrow(()-> new RuntimeException("Inscripcion no encontrada"));

        int idInscr = insc.getIdInscripcion();

        if(pagosDTO.getIdPago() == 0) {
            if (pagosRepository.findPagosByInscripcionesIdInscripcion(idInscr).isPresent()) {
                throw new RuntimeException("Inscripción ya pagada");
            }
        }

        if(pagosDTO.getFechaPago().isBefore(insc.getFechaInicio()))
        {
            throw new RuntimeException("La fecha de pago no puede ser inferior a la fecha de suscripción");
        }

        float valorp = insc.getModalidades().getValor() * calcularOferta(pagosDTO) + pagosDTO.getValori();

        pagos.setValorp(valorp);


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

    @Override
    public float calcularOferta(PagosDTO pagosDTO) {
        String oferta = pagosDTO.getOferta();
        if (oferta.equals("2x1")){
            return 0;
        }
        else if(oferta.equals("50%")){
            return 0.5f;
        }
        else {
            return 1;
        }
    }


    @Override
    public Page<Pagos> findPagos(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber -1,5);
        Page<Pagos> pagos = pagosRepository.findAll(pageable);
        return pagos;
    }

    @Override
    public Page<Pagos> searchPagos(String keyword, int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return pagosRepository.searchPagosByNameOrLastName(keyword,pageable);
    }


    @Override
    public Page<Pagos> obtenerPagosSort(String campo, String direccion, int pageNumber)
    {
        Sort sort = direccion.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(campo).ascending(): Sort.by(campo).descending();

        Pageable pageable = PageRequest.of(pageNumber-1,5, sort);

        return pagosRepository.findAll(pageable);
    }
}
