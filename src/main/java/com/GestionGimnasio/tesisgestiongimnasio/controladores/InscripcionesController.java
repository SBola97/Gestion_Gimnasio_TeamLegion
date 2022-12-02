package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ModalidadesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.InscripcionesService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.ModalidadesService;
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
import java.io.Console;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gym/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionesController {

    @Autowired
    private InscripcionesService inscripcionesService;

    @Autowired
    private ModalidadesService modalidadesService;

    @Autowired
    private PersonasService personasService;


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

        //List<Inscripciones> listaInscripciones = mapper.toInscripciones((List<InscripcionesDTO>)inscripcionesService.obtenerInscripcion());
        List<InscripcionesDTO> listaInscripciones = inscripcionesService.obtenerInscripcion();
        //modelo.addAttribute("personas",personas);
        modelo.addAttribute("listaInscripciones",listaInscripciones);

        return "inscripciones";
    }
    @GetMapping("/formulario")
    public String mostrarFormularioInscripciones(Map<String,Object> modelo)
    {
        InscripcionesDTO inscripcionesDTO = new InscripcionesDTO();
        //PersonasDTO personas = new PersonasDTO();
        List<PersonasDTO> listaPersonasi = personasService.obtenerPersona();
        List<ModalidadesDTO> listaModalidades = modalidadesService.obtenerModalidades();
        modelo.put("titulo","Registro de inscripciones");
        modelo.put("inscripciones",inscripcionesDTO);
        modelo.put("listaPersonas",listaPersonasi);
        modelo.put("listaModalidades",listaModalidades);
        return "inscripciones_form";
    }
    @PostMapping("/guardar")
    public String guardarInscripciones(@Valid @ModelAttribute("inscripciones") InscripcionesDTO inscripcionesDTO, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro de suscripción al gimnasio");
            System.out.println("Errores:"+result.toString());
            return "inscripciones_form";
        }
        String mensaje = Long.valueOf(inscripcionesDTO.getIdInscripcion()) != null ? "Inscripción editada con éxito": "Inscripción registrada con éxito";
        inscripcionesService.ingresarInscripcion(inscripcionesDTO);
        modelo.addAttribute("inscripciones",inscripcionesDTO);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/inscripciones/listar";
    }

    @GetMapping("/guardar/{idInscripcion}")
    public String editarInscripcion(@PathVariable(value = "idInscripcion") int idI, Map<String,Object> modelo,RedirectAttributes flash)
    {
        InscripcionesDTO inscripciones = null;
        List<PersonasDTO> listaPersonas = personasService.obtenerPersona();
        List<ModalidadesDTO> listaModalidades = modalidadesService.obtenerModalidades();

        if(idI>0) {
            inscripciones = inscripcionesService.buscarInscripcion(idI);
            if (inscripciones == null) {
                flash.addFlashAttribute("error", "Inscripción no encontrada en la Base de Datos");
                return "redirect:/gym/inscripciones/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id de la inscripción no puede ser 0");
            return "redirect:/gym/inscripciones/listar";
        }
        modelo.put("inscripciones",inscripciones);
        modelo.put("listaPersonas", listaPersonas);
        modelo.put("titulo","Modificación de inscripción");
        modelo.put("listaModalidades",listaModalidades);
        return "inscripciones_form";
    }

    @GetMapping("/eliminar/{idInscripcion}")
    public String eliminarInscripcion(@PathVariable(value ="idInscripcion") int idI, RedirectAttributes flash)
    {
        if(idI > 0)
        {
            inscripcionesService.eliminarInscripcion(idI);
            flash.addFlashAttribute("success","Inscripción eliminada con éxito");
        }
        return "redirect:/gym/inscripciones/listar";
    }

}
