package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.InscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/gym/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionesController {

    @Autowired
    private InscripcionesService inscripcionesService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<InscripcionesDTO> ingresarInscripcion(@RequestBody @Valid InscripcionesDTO inscripcionesDTO)
    {
        return new ResponseEntity<>(inscripcionesService.ingresarInscripcion(inscripcionesDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public List<InscripcionesDTO> getInscripciones()
    {
        return inscripcionesService.obtenerInscripcion();
    }

    @GetMapping("/listar")
    public String listarInscripciones(Model modelo)
    {
        modelo.addAttribute("listaInscripciones",inscripcionesService.obtenerInscripcion());
        return "inscripciones";
    }
}
