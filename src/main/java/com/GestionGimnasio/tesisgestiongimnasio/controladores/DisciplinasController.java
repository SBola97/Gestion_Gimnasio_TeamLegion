package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.DisciplinasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.DisciplinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinasController {

    @Autowired
    private DisciplinasService disciplinasService;

    @PostMapping
    public ResponseEntity<DisciplinasDTO> ingresarDisciplina(@RequestBody @Valid DisciplinasDTO disciplinasDTO)
    {
        return new ResponseEntity<>(disciplinasService.ingresarDisciplina(disciplinasDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<DisciplinasDTO> getDisciplinas()
    {
        return disciplinasService.obtenerDisciplinas();
    }
}
