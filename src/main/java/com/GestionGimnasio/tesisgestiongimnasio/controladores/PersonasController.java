package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.*;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.*;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.DisciplinasService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.RolesService;
import com.GestionGimnasio.tesisgestiongimnasio.util.paginacion.PageRender;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
        return new ResponseEntity<>(personasService.registrarPersona(personasDTO), HttpStatus.CREATED);
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

    @GetMapping("/search/page/{pageNumber}")
    public String buscarMiembros(@PathVariable("pageNumber") int currentPage, @RequestParam("q") String searchTerm,
                                PersonasDTO personasDTO,Model modelo)
    {
        Page<Personas> page = personasService.searchPersonas(searchTerm,currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Personas> personas = page.getContent();
        modelo.addAttribute("listaPersonas",personas);
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);

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


    @GetMapping("/clientes/searchc/page/{pageNumber}")
    public String buscarClientes(@PathVariable("pageNumber") int currentPage, @RequestParam("q") String searchTerm,
                                 PersonasDTO personasDTO,Model modelo)
    {
        Page<Personas> page = personasService.searchClientes(searchTerm,currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Personas> personas = page.getContent();
        modelo.addAttribute("listaClientes",personas);
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

    @GetMapping("/profesores/searchp/page/{pageNumber}")
    public String buscarProfesores(@PathVariable("pageNumber") int currentPage, @RequestParam("q") String searchTerm,
                                 PersonasDTO personasDTO,Model modelo)
    {
        Page<Personas> page = personasService.searchProfesores(searchTerm,currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Personas> personas = page.getContent();
        modelo.addAttribute("listaProfesores",personas);
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);

        return "profesores";
    }


    @GetMapping("/formulario")
    public String mostrarFormularioPersonas(Map<String,Object> modelo)
    {
        PersonasDTO personas = new PersonasDTO();
        List<Roles> listaRoles = rolesService.getRoles();
        modelo.put("personas",personas);
        modelo.put("listaRoles",listaRoles);
        modelo.put("titulo","Registro para clientes");
        return "miembros_form";
    }

    @GetMapping("/formulariop")
    public String mostrarFormularioProfesores(Map<String,Object> modelo)
    {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        List<Roles> listaRoles = rolesService.getRoles();
        List<DisciplinasDTO> listaDisciplinas = disciplinasService.obtenerDisciplinas();

        modelo.put("personas",profesorDTO);
        modelo.put("listaRoles",listaRoles);
        modelo.put("listaDisciplinas",listaDisciplinas);
        modelo.put("titulo","Registro para profesores");
        return "profesoresForm";
    }
    @PostMapping("/guardar")
    public String guardarPersonas(@Valid @ModelAttribute("personas") PersonasDTO personas, BindingResult result,
                                  Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro para clientes del gimnasio");
            return "miembros_form";
        }
        String mensaje = "Cliente registrado con éxito";
        personasService.registrarPersona(personas);
        modelo.addAttribute("personas", personas);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/personas/clientes/page/1";
    }

    @PostMapping("/guardarp")
    public String guardarProfesores(@Valid @ModelAttribute("personas") ProfesorDTO personas, BindingResult result,
                                    Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro para profesores del gimnasio");
            return "profesoresForm";
        }
        String mensaje = "Profesor registrado con éxito";
        personasService.registrarProfesor(personas);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/personas/profesores/page/1";
    }

    @GetMapping("/guardar/{idPersona}")
    public String editarPersona(@PathVariable(value = "idPersona") int idP, Map<String,Object> modelo,
                                RedirectAttributes flash)
    {
        PersonasDTO personas = null;
        if(idP>0) {
            personas = personasService.buscarPersona(idP);
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
        modelo.put("titulo","Modificación de cliente del gimnasio");
        return "miembros_form";
    }

    @GetMapping("/guardarp/{idPersona}")
    public String editarProfesor(@PathVariable(value = "idPersona") int idP, Map<String,Object> modelo,
                                 RedirectAttributes flash)
    {
        ProfesorDTO profesorDTO = null;
        List<DisciplinasDTO> listaDisciplinas = disciplinasService.obtenerDisciplinas();

        if(idP>0) {
            profesorDTO = personasService.buscarProfesor(idP);
            if (profesorDTO == null) {
                flash.addFlashAttribute("error", "Profesor no encontrado en la Base de Datos");
                return "redirect:/gym/personas/profesores/page/1";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id del integrante no puede ser 0");
            return "redirect:/gym/personas/listar";
        }
        modelo.put("personas",profesorDTO);
        modelo.put("listaDisciplinas",listaDisciplinas);
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
