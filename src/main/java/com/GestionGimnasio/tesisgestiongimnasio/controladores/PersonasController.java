package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ClienteDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ProfesorDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    //Controladores para vistas (Front-End)
    @GetMapping("/listar")
    public String listarPersonas(Model modelo)
    {
        modelo.addAttribute("listaPersonas",personasService.obtenerPersona());
        return "miembros";
    }

    @GetMapping("/form")
    public String mostrarFormularioPersonas(Map<String,Object> modelo)
    {
        PersonasDTO personas = new PersonasDTO();
        modelo.put("personas",personas);
        modelo.put("titulo","Registro de miembro");
        return "miembros_form";
    }
    @PostMapping("/form")
    public String guardarPersonas(@Valid PersonasDTO personas, BindingResult result, Model modelo
            , RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro de miembro del gimnasio");
            return "miembros_form";
        }
        String mensaje = (Long.valueOf(personas.getIdPersona()) != null) ? "Miembro editado con éxito": "Miembro registrado con éxito";
        personasService.ingresarPersona(personas);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/personas/listar";
    }

    @GetMapping("/form/{idPersona}")
    public String editarMiembro(@PathVariable(value = "idPersona") int idP, Map<String,Object> modelo,RedirectAttributes flash)
    {
        PersonasDTO personas = null;
        if(idP>0) {
            personas = personasService.buscarPersona(idP);
            if (personas == null) {
                flash.addFlashAttribute("error", "Miembro no encontrado en la Base de Datos");
                return "redirect:/gym/personas/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id del miembro no puede ser 0");
            return "redirect:/gym/personas/listar";
        }
        modelo.put("personas",personas);
        modelo.put("titulo","Modificación de miembro del gimnasio");
        return "miembros_form";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminarMiembro(@PathVariable(value ="idPersona") int idP, RedirectAttributes flash)
    {
        if(idP > 0)
        {
            personasService.eliminarPersona(idP);
            flash.addFlashAttribute("success","Miembro eliminado con éxito");
        }
        return "redirect:/gym/personas/listar";
    }
}
