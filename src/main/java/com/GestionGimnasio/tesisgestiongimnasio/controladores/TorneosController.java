package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.TorneosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.TorneosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/torneos")
@CrossOrigin(origins = "*")
public class TorneosController {

    @Autowired
    private TorneosService torneosService;

    @PostMapping
    public ResponseEntity<TorneosDTO> ingresarTorneo(@RequestBody @Valid TorneosDTO torneosDTO)
    {
        return new ResponseEntity<>(torneosService.ingresarTorneo(torneosDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<TorneosDTO> getTorneos(){
        return torneosService.obtenerTorneos();
    }
}
