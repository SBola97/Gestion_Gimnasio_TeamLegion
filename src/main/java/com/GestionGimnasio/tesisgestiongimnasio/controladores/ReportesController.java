package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteDeudoresDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteGananciasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PagosRepository;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PagosService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/gym/reportes")
@CrossOrigin(origins = "*")
public class ReportesController {


    @Autowired
    ReporteService reporteService;

    @Autowired
    PagosService pagosService;

    @GetMapping()
    public String menuReportes()
    {
        return "reportes";
    }

    @GetMapping("/gananciasMensuales")
    public String reporteGananciasM(Model modelo)
    {
        ReporteGananciasDTO reporteGananciasDTO = new ReporteGananciasDTO();
        modelo.addAttribute("reporteGanancias",reporteGananciasDTO);
        //modelo.addAttribute("listaPagosM",reporteService.obtenerPagosMes(reporteGananciasDTO));
        return "reporteGananciasM";
    }

    @GetMapping("/deudores")
    public String reporteDeudores(Model modelo)
    {
        ReporteDeudoresDTO reporteDeudoresDTO = new ReporteDeudoresDTO();
        modelo.addAttribute("reporteDeudores",reporteDeudoresDTO);
        return "reporteDeudores";
    }
    @PostMapping("/mesd")
    public String buscarMes(@Valid @ModelAttribute("reporteDeudores") ReporteDeudoresDTO reporteDeudoresDTO,
                            Model modelo ,BindingResult result)
    {
        if(result.hasErrors())
        {
            System.out.println("Errores: "+result.toString());
            return "reportes";
        }
        modelo.addAttribute("reporteDeudores",reporteDeudoresDTO);
        modelo.addAttribute("listaDeudores",reporteService.obtenerDeudoresMes(reporteDeudoresDTO));
        return "reporteDeudores";
    }


    @PostMapping("/mes")
    public String buscarMes(@Valid @ModelAttribute("reporteGanacias") ReporteGananciasDTO reporteGananciasDTO,
                            Model modelo ,BindingResult result)
    {
        if(result.hasErrors())
        {
            System.out.println("Errores: "+result.toString());
            return "reportes";
        }
        modelo.addAttribute("reporteGanancias",reporteGananciasDTO);
        modelo.addAttribute("listaPagosM",reporteService.obtenerPagosMes(reporteGananciasDTO));
        reporteService.obtenerGanancias(reporteGananciasDTO);
        return "reporteGananciasM";
    }
}
