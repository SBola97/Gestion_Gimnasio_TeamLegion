package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.InscripcionesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ModalidadesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.InscripcionesService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.ModalidadesService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.Console;
import java.sql.SQLOutput;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
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

    @GetMapping("/listar/page/{pageNumber}")
    public String listarInscripciones(@PathVariable("pageNumber") int currentPage,Model modelo)
    {
        Page<Inscripciones> page = inscripcionesService.obtenerInscripciones(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Inscripciones> inscripciones = page.getContent();
        //List<Inscripciones> listaInscripciones = mapper.toInscripciones((List<InscripcionesDTO>)inscripcionesService.obtenerInscripcion());
        inscripcionesService.verificarInscripcionesVencidas();
        //List<InscripcionesDTO> listaInscripciones = inscripcionesService.obtenerInscripcion();
        //modelo.addAttribute("personas",personas);
        modelo.addAttribute("listaInscripciones",inscripciones);
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);

        return "inscripciones";
    }

    @GetMapping("/listar/page/{pageNumber}/{campo}")
    public String listarInscripcionesOrd(@PathVariable("pageNumber") int currentPage, Model modelo, @PathVariable
                                    String campo, @PathParam("sortDir") String sortDir)
    {
        Page<Inscripciones> page = inscripcionesService.obtenerInscripcionesSort(campo,sortDir, currentPage);
        List<Inscripciones> inscripciones = page.getContent();
        inscripcionesService.verificarInscripcionesVencidas();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        modelo.addAttribute("listaInscripciones",inscripciones);
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);
        modelo.addAttribute("sortDir",sortDir);
        modelo.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");
        return "inscripciones";
    }

    @GetMapping("/porvencer")
    public String listarInscripcionesPV(Model modelo)
    {

        //List<Inscripciones> listaInscripciones = mapper.toInscripciones((List<InscripcionesDTO>)inscripcionesService.obtenerInscripcion());
        List<InscripcionesDTO> listaInscripcionesPV = inscripcionesService.obtenerInscripcionesPorVencer();
        //modelo.addAttribute("personas",personas);
        modelo.addAttribute("listaInscripcionesPV",listaInscripcionesPV);

        return "inscripcionesPorVencer";
    }

    @GetMapping("/notificar/{idInscripcion}")
    public String sendMessage(@PathVariable(value = "idInscripcion") int idI, RedirectAttributes flash, Model modelo)
    {
        Inscripciones inscripcion = null;
        //List<InscripcionesDTO> listaClientesNotificar = inscripcionesService.obtenerInscripcionesPorVencer();
        String telef = "";
        String name = "";
        String fechaf = "";
        if(idI>0)
        {
            inscripcion = inscripcionesService.findInscripcion(idI);
            telef = inscripcion.getPersonas().getTelefono();
            name = inscripcion.getPersonas().getNombre() +' '+ inscripcion.getPersonas().getApellidos();
            fechaf = String.valueOf(inscripcion.getFechaFin().format(DateTimeFormatter.
                    ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale ("es","ES"))));
        }
        List<InscripcionesDTO> listaInscripcionesPV = inscripcionesService.obtenerInscripcionesPorVencer();

        final String ACCOUNT_SID = "AC6b2e5744962cbc52c19f965447264b02";
        final String AUTH_TOKEN = "9c554e988f7b980ef814fb5cb75f35b8";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("+593"+telef),
                "MGc55ae82b1c7f5ebe8232148a488d6f86",
                "Estimado cliente, "+ name +", su suscripción del Gimnasio 'Team Legión' Riobamba vencerá el "
                + fechaf + ". Por favor, mantenga sus pagos y suscripciones al día."
        ).create();
        modelo.addAttribute("listaInscripcionesPV",listaInscripcionesPV);
        flash.addFlashAttribute("success", "SMS enviado con éxito");
        return "redirect:/gym/inscripciones/porvencer";
    }
    @GetMapping("/formulario")
    public String mostrarFormularioInscripciones(Map<String,Object> modelo)
    {
        InscripcionesDTO inscripcionesDTO = new InscripcionesDTO();
        //PersonasDTO personas = new PersonasDTO();
        //List<PersonasDTO> listaPersonasi = personasService.obtenerPersona();
        List<PersonasDTO> listaPersonasi = personasService.obtenerPersonasSinSuscripcion();
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
        String mensaje = "Inscripción registrada con éxito";
        inscripcionesService.ingresarInscripcion(inscripcionesDTO);
        modelo.addAttribute("inscripciones",inscripcionesDTO);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/inscripciones/listar/page/1";
    }

    @GetMapping("/guardar/{idInscripcion}")
    public String editarInscripcion(@PathVariable(value = "idInscripcion") int idI, Map<String,Object> modelo,RedirectAttributes flash)
    {
        InscripcionesDTO inscripciones = null;
        List<PersonasDTO> listaPersonas = personasService.obtenerPersonasSinSuscripcion();
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
        //añadir a la lista de personas sin suscripción el cliente ya registrado para poder editarlo
        listaPersonas.add(personasService.buscarPersona(inscripciones.getIdPersona()));
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
        return "redirect:/gym/inscripciones/listar/page/1";
    }

}
