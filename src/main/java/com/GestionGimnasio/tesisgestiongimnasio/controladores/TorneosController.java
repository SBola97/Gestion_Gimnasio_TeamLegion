package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.TorneosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.TorneosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/gym/torneos")
@CrossOrigin(origins = "*")
public class TorneosController {

    @Autowired
    private TorneosService torneosService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<TorneosDTO> ingresarTorneo(@RequestBody @Valid TorneosDTO torneosDTO)
    {
        return new ResponseEntity<>(torneosService.ingresarTorneo(torneosDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public List<TorneosDTO> getTorneos(){
        return torneosService.obtenerTorneos();
    }

    @GetMapping("/listar")
    public String listarTorneos(Model modelo)
    {
        modelo.addAttribute("listaTorneos",torneosService.obtenerTorneos());
        return "torneos";
    }

}
