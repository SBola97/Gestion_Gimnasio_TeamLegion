package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/personas")
@CrossOrigin(origins = "*")
public class PersonasController {

    @Autowired
    private PersonasService personasService;

    @PostMapping
    public ResponseEntity<PersonasDTO> ingresarPersona(@RequestBody @Valid PersonasDTO personasDTO)
    {
        return new ResponseEntity<>(personasService.ingresarPersona(personasDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PersonasDTO> getPersonas (){
        return personasService.obtenerPersona();
    }

}
