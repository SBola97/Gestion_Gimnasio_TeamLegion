package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/pagos")
@CrossOrigin(origins = "*")
public class PagosController {

    @Autowired
    PagosService pagosService;

    @PostMapping
    public ResponseEntity<PagosDTO> ingresarInscripcion(@RequestBody @Valid PagosDTO pagosDTO)
    {
        return new ResponseEntity<>(pagosService.ingresarPago(pagosDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PagosDTO> getPagos()
    {
        return pagosService.obtenerPagos();

    }
}
