package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.ModalidadesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Modalidades;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.ModalidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/modalidades")
@CrossOrigin(origins = "*")
public class ModalidadesController {

    @Autowired
    private ModalidadesService modalidadesService;

    @PostMapping
    public ResponseEntity<ModalidadesDTO> ingresarDisciplina(@RequestBody @Valid ModalidadesDTO modalidadesDTO)
    {
        return new ResponseEntity<>(modalidadesService.ingresarModalidad(modalidadesDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ModalidadesDTO> getModalidades(){
        return modalidadesService.obtenerModalidades();
    }
}
