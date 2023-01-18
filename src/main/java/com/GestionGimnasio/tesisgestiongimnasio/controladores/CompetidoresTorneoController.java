package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.CompetidoresTorneoDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.CompetidorTorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/gym/competidores")
@CrossOrigin(origins = "*")
public class CompetidoresTorneoController {

    @Autowired
    private CompetidorTorneoService competidorTorneoService;

    @PostMapping
    public ResponseEntity<CompetidoresTorneoDTO>ingresarCompetidorTorneo
            (@RequestBody @Valid CompetidoresTorneoDTO competidoresTorneoDTO){
        return new ResponseEntity<>(competidorTorneoService.ingresarCompetidorTorneo(competidoresTorneoDTO)
                , HttpStatus.CREATED);
    }

    @GetMapping
    public List<CompetidoresTorneoDTO> getCompetidoresTorneo(){
        return competidorTorneoService.obtenerCompetidoresTorneo();
    }

    @GetMapping("/listar")
    public String listarCompetidores(Model modelo)
    {
        modelo.addAttribute("listaCompetidores",competidorTorneoService.obtenerCompetidoresTorneo());
        return "competidores";
    }
}
