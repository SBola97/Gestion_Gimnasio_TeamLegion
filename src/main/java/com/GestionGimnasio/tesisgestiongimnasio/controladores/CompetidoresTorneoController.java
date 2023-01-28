package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.*;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo_Key;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Torneos;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.FichaMapper;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.CompetidorTorneoService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.TorneosService;
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
@RequestMapping("/gym/competidores")
@CrossOrigin(origins = "*")
public class CompetidoresTorneoController {

    @Autowired
    private CompetidorTorneoService competidorTorneoService;

    @Autowired
    private PersonasService personasService;

    @Autowired
    private TorneosService torneosService;

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

    @GetMapping("/ficha/{idPersona}")
    public String fichaCompetidor(@PathVariable(value ="idPersona") int idP,Model modelo, RedirectAttributes flash)
    {
        try {
            PersonasDTO competidor = personasService.buscarPersona(idP);
            List<FichaDTO> fichaCompetidor = competidorTorneoService.obtenerFichaCompetidor(idP);
            List<PersonasDTO> listaPersonas = personasService.obtenerPersona();
            List<TorneosDTO> listaTorneos = torneosService.obtenerTorneos();
            modelo.addAttribute("fichaCompetidor",fichaCompetidor);
            modelo.addAttribute("competidor",competidor);
            modelo.addAttribute("listaPersonas",listaPersonas);
            modelo.addAttribute("listaTorneos",listaTorneos);
            return "ficha_competidor";
        }
        catch (RuntimeException e)
        {
            flash.addFlashAttribute("error",e.toString());
            return "competidores";
        }

    }


    @GetMapping("/formulario")
    public String mostrarFormularioCompetidores(Map<String,Object> modelo)
    {
        CompetidoresTorneoDTO competidoresTorneoDTO = new CompetidoresTorneoDTO();

        //Competidores_Torneo_Key id = new Competidores_Torneo_Key();

        List<PersonasDTO> listaPersonas = personasService.obtenerPersona();
        List<TorneosDTO> listaTorneos = torneosService.obtenerTorneos();
        modelo.put("titulo","Registro de competidores");
        modelo.put("competidores_torneo",competidoresTorneoDTO);
        modelo.put("listaPersonas",listaPersonas);
        modelo.put("listaTorneos",listaTorneos);
        return "competidores_form";
    }

    @PostMapping("/guardar")
    public String guardarCompetidores(@Valid @ModelAttribute("competidores_torneo")
                                          CompetidoresTorneoDTO competidoresTorneoDTO, BindingResult result,
                                      Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {

            modelo.addAttribute("titulo","Registro de competidores");
            System.out.println("Errores:"+result.toString());
            return "competidores_form";
        }

/*        Personas personas = new Personas();
        Torneos torneos = new Torneos();

        Competidores_Torneo_Key competidores_torneo_key = new Competidores_Torneo_Key();
        competidores_torneo_key.setTorneo_id((long) torneos.getIdTorneo());
        competidores_torneo_key.setPersona_id((long) personas.getIdPersona());
        Competidores_Torneo_Key competidores_torneo_key1 = new Competidores_Torneo_Key((long) torneos.getIdTorneo(), (long) personas.getIdPersona());
        competidoresTorneoDTO.setCompetidores_Torneo_Key(competidores_torneo_key1);*/

        String mensaje = "Competidor registrado con éxito";
        competidorTorneoService.ingresarCompetidorTorneo(competidoresTorneoDTO);
        //competidorTorneoService.ingresarCompetidores(competidoresTorneoDTO);
        modelo.addAttribute("competidores_torneo",competidoresTorneoDTO);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/competidores/listar";
    }


    @GetMapping("/guardar/{idCompetidorTorneo}")
    public String editarCompetidor(@PathVariable(value = "idCompetidorTorneo") int idCt, Map<String,Object> modelo,RedirectAttributes flash)
    {
        CompetidoresTorneoDTO competidoresTorneoDTO = null;
        List<PersonasDTO> listaPersonas = personasService.obtenerPersona();
        List<TorneosDTO> listaTorneos = torneosService.obtenerTorneos();

        if(idCt>0) {
            competidoresTorneoDTO = competidorTorneoService.buscarCompetidorTorneo(idCt);
            if (competidoresTorneoDTO == null) {
                flash.addFlashAttribute("error", "Competidor no encontrado en la Base de Datos");
                return "redirect:/gym/competidores/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id de competidor no puede ser 0");
            return "redirect:/gym/competidores/listar";
        }
        modelo.put("titulo","Modificación de competidor");
        modelo.put("competidores_torneo",competidoresTorneoDTO);
        modelo.put("listaPersonas",listaPersonas);
        modelo.put("listaTorneos",listaTorneos);
        return "competidores_form";

    }

    @GetMapping("/eliminar/{idCompetidorTorneo}")
    public String eliminarCompetidor(@PathVariable(value ="idCompetidorTorneo") int idCt,RedirectAttributes flash)
    {
        if(idCt>0)
        {
            competidorTorneoService.eliminarCompetidorTorneo(idCt);
            flash.addFlashAttribute("success","Competidor eliminado con éxito");
        }
        return "redirect:/gym/competidores/listar";
    }

}
