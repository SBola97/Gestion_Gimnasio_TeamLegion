package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.servicios.InscripcionesService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PagosService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.TorneosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gym/dashboard")
@CrossOrigin(origins = "*")
public class VistaController {
    @Autowired
    private PersonasService personasService;

    @Autowired
    private InscripcionesService inscripcionesService;

    @Autowired
    private PagosService pagosService;

    @Autowired
    private TorneosService torneosService;


    @GetMapping("/miembros")
    public String listarPersonas(Model modelo)
    {
        modelo.addAttribute("listaPersonas",personasService.obtenerPersona());
        return "miembros";
    }

    @GetMapping("/inscripciones")
    public String listarInscripciones(Model modelo)
    {
        modelo.addAttribute("listaInscripciones",inscripcionesService.obtenerInscripcion());
        return "inscripciones";
    }

    @GetMapping("/pagos")
    public String listarPagos(Model modelo)
    {
        modelo.addAttribute("listaPagos",pagosService.obtenerPagos());
        return "pagos";
    }

    @GetMapping("/torneos")
    public String listarTorneos(Model modelo)
    {
        modelo.addAttribute("listaTorneos",torneosService.obtenerTorneos());
        return "torneos";
    }


}
