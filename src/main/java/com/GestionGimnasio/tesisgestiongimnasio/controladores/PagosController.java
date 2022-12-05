package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.*;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.FormasPagoService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.InscripcionesService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PagosService;
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
@RequestMapping("/gym/pagos")
@CrossOrigin(origins = "*")
public class PagosController {

    @Autowired
    PagosService pagosService;

    @Autowired
    InscripcionesService inscripcionesService;

    @Autowired
    FormasPagoService formasPagoService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<PagosDTO> ingresarPago(@RequestBody @Valid PagosDTO pagosDTO)
    {
        return new ResponseEntity<>(pagosService.ingresarPago(pagosDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public List<PagosDTO> getPagos()
    {
        return pagosService.obtenerPagos();

    }
    @GetMapping("/listar")
    public String listarPagos(Model modelo)
    {
        modelo.addAttribute("listaPagos",pagosService.obtenerPagos());
        return "pagos";
    }

    @GetMapping("/formulario")
    public String mostrarFormularioPagos(Map<String,Object> modelo)
    {
        PagosDTO pagosDTO = new PagosDTO();
        List<InscripcionesDTO> listaInscripciones = inscripcionesService.obtenerInscripcion();
        List<FormaPagoDTO> listaFormaPago = formasPagoService.obtenerFormasPago();
        modelo.put("titulo","Registro de pagos");
        modelo.put("pagos",pagosDTO);
        modelo.put("listaInscripciones",listaInscripciones);
        modelo.put("listaFormaPago",listaFormaPago);
        return "pagos_form";
    }

    @PostMapping("/guardar")
    public String guardarPagos(@Valid @ModelAttribute("pagos") PagosDTO pagosDTO, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro de pago");
            System.out.println("Errores:"+result.toString());
            return "pagos_form";
        }
        String mensaje = "Pago registrado con éxito";
        pagosService.ingresarPago(pagosDTO);
        modelo.addAttribute("pagos",pagosDTO);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/pagos/listar";
    }
    @GetMapping("/guardar/{idPago}")
    public String editarPago(@PathVariable(value = "idPago") int idP, Map<String,Object> modelo,RedirectAttributes flash)
    {
        PagosDTO pagosDTO = null;
        List<InscripcionesDTO> listaInscripciones = inscripcionesService.obtenerInscripcion();
        List<FormaPagoDTO> listaFormaPago = formasPagoService.obtenerFormasPago();

        if(idP>0) {
            pagosDTO = pagosService.buscarPago(idP);
            if (pagosDTO == null) {
                flash.addFlashAttribute("error", "Pago no encontrado en la Base de Datos");
                return "redirect:/gym/pagos/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id de pago no puede ser 0");
            return "redirect:/gym/pagos/listar";
        }
        modelo.put("titulo","Modificación de pago");
        modelo.put("pagos",pagosDTO);
        modelo.put("listaInscripciones",listaInscripciones);
        modelo.put("listaFormaPago",listaFormaPago);
        return "pagos_form";

    }
    @GetMapping("/eliminar/{idPago}")
    public String eliminarPago(@PathVariable(value ="idPago") int idP, RedirectAttributes flash)
    {
        if(idP>0)
        {
            pagosService.eliminarPago(idP);
            flash.addFlashAttribute("success","Pago eliminado con éxito");
        }
        return "redirect:/gym/pagos/listar";
    }

}
