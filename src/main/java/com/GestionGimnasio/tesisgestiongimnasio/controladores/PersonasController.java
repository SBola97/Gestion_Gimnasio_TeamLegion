package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ClienteDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ProfesorDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/gym/personas")
@CrossOrigin(origins = "*")
public class PersonasController {

    @Autowired

    private PersonasService personasService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<PersonasDTO> ingresarPersona(@RequestBody @Valid PersonasDTO personasDTO)
    {
        return new ResponseEntity<>(personasService.ingresarPersona(personasDTO), HttpStatus.CREATED);
    }
    @GetMapping
    @ResponseBody
    public List<PersonasDTO> getPersonas (){
        return personasService.obtenerPersona();
    }


    @GetMapping("/clientes")
    @ResponseBody
    public List<ClienteDTO> getClientes(){
        return personasService.obtenerClientes();
    }

    @GetMapping("/profesores")
    @ResponseBody
    public List<ProfesorDTO> getProfesores(){
        return personasService.obtenerProfesores();
    }

    @GetMapping("/listar")
    public String listarPersonas(Model modelo)
    {
        modelo.addAttribute("listaPersonas",personasService.obtenerPersona());
        return "miembros";
    }


}
