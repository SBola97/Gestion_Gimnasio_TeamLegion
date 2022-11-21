package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.InscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionesController {

    @Autowired
    private InscripcionesService inscripcionesService;

    @PostMapping
    public ResponseEntity<InscripcionesDTO> ingresarInscripcion(@RequestBody @Valid InscripcionesDTO inscripcionesDTO)
    {
        return new ResponseEntity<>(inscripcionesService.ingresarInscripcion(inscripcionesDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<InscripcionesDTO> getInscripciones()
    {
        return inscripcionesService.obtenerInscripcion();
    }

}
