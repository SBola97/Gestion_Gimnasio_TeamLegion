package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.FormaPagoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.FormaPago;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.FormasPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/formasPago")
@CrossOrigin(origins = "*")
public class FormasPagoController {

    @Autowired
    private FormasPagoService formasPagoService;

    @PostMapping
    public ResponseEntity<FormaPagoDTO> ingresarFormaPago(@RequestBody @Valid FormaPagoDTO formaPagoDTO)
    {
        return new ResponseEntity<>(formasPagoService.ingresarFormaPago(formaPagoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<FormaPagoDTO> getFormasPago(){
        return formasPagoService.obtenerFormasPago();
    }

}
