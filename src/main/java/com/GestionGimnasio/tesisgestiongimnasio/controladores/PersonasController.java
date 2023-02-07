package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ClienteDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.DisciplinasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ProfesorDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Disciplinas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.DisciplinasService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.RolesService;
import com.GestionGimnasio.tesisgestiongimnasio.util.paginacion.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private RolesService rolesService;

    @Autowired
    private DisciplinasService disciplinasService;

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


/*    @GetMapping("/clientes")
    @ResponseBody
    public List<ClienteDTO> getClientes(){
        return personasService.obtenerClientes();
    }

    @GetMapping("/profesores")
    @ResponseBody
    public List<ProfesorDTO> getProfesores(){
        return personasService.obtenerProfesores();
    }*/

    //Controladores para vistas (Front-End)
    @GetMapping("/listar/page/{pageNumber}")
    public String listarPersonas(@PathVariable("pageNumber") int currentPage,Model modelo)
    {
        Page<Personas> page = personasService.obtenerSuscriptores(currentPage);
        //PageRender<Personas> pageRender = new PageRender<>("/listar",personas);
        //List<Personas> listaPersonas = personasService.obtenerPersonas();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Personas> personas = page.getContent();
        modelo.addAttribute("listaPersonas",personas);
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);
        //modelo.addAttribute("page",pageRender);
        return "miembros";
    }

    @GetMapping("/clientes/page/{pageNumber}")
    public String listarClientes(@PathVariable("pageNumber") int currentPage, Model modelo)
    {
        Page<Personas> page = personasService.obtenerClientes(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Personas> listaClientes = page.getContent();
        modelo.addAttribute("listaClientes",listaClientes);
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);
        return "clientes";
    }

    @GetMapping("/profesores/page/{pageNumber}")
    public String listarProfesores(@PathVariable("pageNumber") int currentPage,Model modelo)
    {
        Page<Personas> page = personasService.obtenerProfesores(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Personas> listaProfesores = page.getContent();
        //List<ProfesorDTO> listaProfesores = personasService.obtenerProfesores();
        modelo.addAttribute("listaProfesores",listaProfesores);
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);
        return "profesores";
    }


    @GetMapping("/formulario")
    public String mostrarFormularioPersonas(Map<String,Object> modelo)
    {
        Personas personas = new Personas();
        List<Roles> listaRoles = rolesService.getRoles();
        List<DisciplinasDTO> listaDisciplinas = disciplinasService.obtenerDisciplinas();

        modelo.put("personas",personas);
        modelo.put("listaRoles",listaRoles);
        modelo.put("listaDisciplinas",listaDisciplinas);
        modelo.put("titulo","Registro para integrantes del gimnasio");
        return "miembros_form";
    }

    @GetMapping("/formulariop")
    public String mostrarFormularioProfesores(Map<String,Object> modelo)
    {
        Personas personas = new Personas();
        List<Roles> listaRoles = rolesService.getRoles();
        List<DisciplinasDTO> listaDisciplinas = disciplinasService.obtenerDisciplinas();

        modelo.put("personas",personas);
        modelo.put("listaRoles",listaRoles);
        modelo.put("listaDisciplinas",listaDisciplinas);
        modelo.put("titulo","Registro para profesores del gimnasio");
        return "profesoresForm";
    }
    @PostMapping("/guardar")
    public String guardarPersonas(@Valid Personas personas, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro para integrantes del gimnasio");
            return "miembros_form";
        }
        String mensaje = "Integrante registrado con éxito";
        personasService.registrarPersona(personas);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/personas/listar/page/1";
    }

    @GetMapping("/guardar/{idPersona}")
    public String editarPersona(@PathVariable(value = "idPersona") int idP, Map<String,Object> modelo,RedirectAttributes flash)
    {
        Personas personas = null;
        Roles roles = new Roles();
        List<Roles> listaRoles = rolesService.getRoles();

        if(idP>0) {
            personas = personasService.findPersona(idP);
            if (personas == null) {
                flash.addFlashAttribute("error", "Persona no encontrada en la Base de Datos");
                return "redirect:/gym/personas/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id del integrante no puede ser 0");
            return "redirect:/gym/personas/listar";
        }
        modelo.put("personas",personas);
        //modelo.put("roles",roles);
        modelo.put("listaRoles",listaRoles);
        modelo.put("titulo","Modificación de integrante del gimnasio");
        return "miembros_form";
    }

    @GetMapping("/guardarp/{idPersona}")
    public String editarProfesor(@PathVariable(value = "idPersona") int idP, Map<String,Object> modelo,RedirectAttributes flash)
    {
        Personas personas = null;
        Roles roles = new Roles();
        List<Roles> listaRoles = rolesService.getRoles();
        List<DisciplinasDTO> listaDisciplinas = disciplinasService.obtenerDisciplinas();

        if(idP>0) {
            personas = personasService.findPersona(idP);
            if (personas == null) {
                flash.addFlashAttribute("error", "Persona no encontrada en la Base de Datos");
                return "redirect:/gym/personas/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id del integrante no puede ser 0");
            return "redirect:/gym/personas/listar";
        }
        modelo.put("personas",personas);
        modelo.put("listaDisciplinas",listaDisciplinas);
        //modelo.put("roles",roles);
        modelo.put("listaRoles",listaRoles);
        modelo.put("titulo","Modificación de profesor del gimnasio");
        return "profesoresForm";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminarMiembro(@PathVariable(value ="idPersona") int idP, RedirectAttributes flash)
    {
        if(idP > 0)
        {
            personasService.eliminarPersona(idP);
            flash.addFlashAttribute("success","Integrante eliminado con éxito");
        }
        return "redirect:/gym/personas/listar/page/1";
    }
    
}
