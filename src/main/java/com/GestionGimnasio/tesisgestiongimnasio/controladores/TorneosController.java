package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.*;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.DisciplinasService;
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
@RequestMapping("/gym/torneos")
@CrossOrigin(origins = "*")
public class TorneosController {

    @Autowired
    private TorneosService torneosService;

    @Autowired
    private DisciplinasService disciplinasService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<TorneosDTO> ingresarTorneo(@RequestBody @Valid TorneosDTO torneosDTO)
    {
        return new ResponseEntity<>(torneosService.ingresarTorneo(torneosDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public List<TorneosDTO> getTorneos(){
        return torneosService.obtenerTorneos();
    }

    @GetMapping("/listar")
    public String listarTorneos(Model modelo)
    {
        modelo.addAttribute("listaTorneos",torneosService.obtenerTorneos());
        return "torneos";
    }
    @GetMapping("/formulario")
    public String mostrarFormularioTorneos(Map<String,Object> modelo)
    {
        TorneosDTO torneosDTO = new TorneosDTO();
        List<DisciplinasDTO> listaDisciplinas = disciplinasService.obtenerDisciplinas();
        modelo.put("titulo","Registro de torneos");
        modelo.put("torneos",torneosDTO);
        modelo.put("listaDisciplinas",listaDisciplinas);
        return "torneos_form";
    }

    @PostMapping("/guardar")
    public String guardarTorneos(@Valid @ModelAttribute("torneos") TorneosDTO torneosDTO, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro de torneo");
            System.out.println("Errores:"+result.toString());
            return "torneos_form";
        }
        String mensaje = "Torneo registrado con éxito";
        torneosService.ingresarTorneo(torneosDTO);
        modelo.addAttribute("torneos",torneosDTO);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/torneos/listar";
    }

    @GetMapping("/guardar/{idTorneo}")
    public String editarTorneo(@PathVariable(value = "idTorneo") int idT, Map<String,Object> modelo,RedirectAttributes flash)
    {
        TorneosDTO torneosDTO = null;
        List<DisciplinasDTO> listaDisciplinas = disciplinasService.obtenerDisciplinas();

        if(idT>0) {
            torneosDTO = torneosService.buscarTorneo(idT);

            if (torneosDTO == null) {
                flash.addFlashAttribute("error", "Torneo no encontrado en la Base de Datos");
                return "redirect:/gym/torneos/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id de torneo no puede ser 0");
            return "redirect:/gym/torneos/listar";
        }
        modelo.put("titulo","Modificación de torneos");
        modelo.put("torneos",torneosDTO);
        modelo.put("listaDisciplinas",listaDisciplinas);

        return "torneos_form";

    }


    @GetMapping("/eliminar/{idTorneo}")
    public String eliminarPago(@PathVariable(value ="idTorneo") int idT, RedirectAttributes flash)
    {
        if(idT>0)
        {
            torneosService.eliminarTorneo(idT);
            flash.addFlashAttribute("success","Torneo eliminado con éxito");
        }
        return "redirect:/gym/torneos/listar";
    }

}
