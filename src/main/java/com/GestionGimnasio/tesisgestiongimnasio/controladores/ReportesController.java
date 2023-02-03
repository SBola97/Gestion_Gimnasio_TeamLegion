package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteAnualDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteDeudoresDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteGananciasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PagosService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.ReporteService;
import com.GestionGimnasio.tesisgestiongimnasio.util.reportes.DeudoresExporterPDF;
import com.GestionGimnasio.tesisgestiongimnasio.util.reportes.GananciasAExporterPDF;
import com.GestionGimnasio.tesisgestiongimnasio.util.reportes.GananciasExporterPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/gananciasAnuales")
    public String reporteGananciasA(Model modelo)
    {
        ReporteAnualDTO reporteAnualDTO = new ReporteAnualDTO();
        modelo.addAttribute("reporteGananciasA",reporteAnualDTO);
        modelo.addAttribute("listaAnual",reporteService.obtenerGananciasAnio());
        return "reporteGananciasA";
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
                            Model modelo ,BindingResult result) {
        List<ReporteDeudoresDTO> listaDeudores = reporteService.obtenerDeudoresMes(reporteDeudoresDTO);
        if(result.hasErrors())
        {
            System.out.println("Errores: "+result.toString());
            return "reportes";
        }

        modelo.addAttribute("reporteDeudores",reporteDeudoresDTO);
        modelo.addAttribute("listaDeudores",listaDeudores);
        return "reporteDeudores";
    }


    @PostMapping("/mes")
    public String buscarMesG(@Valid @ModelAttribute("reporteGanacias") ReporteGananciasDTO reporteGananciasDTO,
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

    @GetMapping("/pdf")
    public void exportarDeudores(HttpServletResponse response, ReporteDeudoresDTO deudoresDTO) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaactual = dateFormat.format(new Date());

        String cabecera = "Content-Disposition";

        String valor = "attachment; filename=Deudores_"+ fechaactual+".pdf";

        response.setHeader(cabecera,valor);
        deudoresDTO.setMes(LocalDate.now().getMonth().getValue());
        List<ReporteDeudoresDTO> listaDeudores = reporteService.obtenerDeudoresMes(deudoresDTO);
        DeudoresExporterPDF exporter = new DeudoresExporterPDF(listaDeudores);
        exporter.exportar(response);
    }

    @GetMapping("/pdfg")
    public void exportarGanancias(HttpServletResponse response, ReporteGananciasDTO reporteGananciasDTO) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaactual = dateFormat.format(new Date());

        String cabecera = "Content-Disposition";

        String valor = "attachment; filename=GananciasMensuales_"+ fechaactual+".pdf";

        response.setHeader(cabecera,valor);
        reporteGananciasDTO.setMes(LocalDate.now().getMonth().getValue());
        BigDecimal ganancias = reporteService.obtenerGanancias(reporteGananciasDTO);
        reporteGananciasDTO.setTotal(ganancias);
        List<ReporteGananciasDTO> listaGanancias = reporteService.obtenerPagosMes(reporteGananciasDTO);

        GananciasExporterPDF exporter = new GananciasExporterPDF(listaGanancias, ganancias);
        exporter.exportar(response);
    }

    @GetMapping("/pdfa")
    public void exportarGananciasA(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaactual = dateFormat.format(new Date());

        String cabecera = "Content-Disposition";

        String valor = "attachment; filename=GananciasAño_"+ fechaactual+".pdf";

        response.setHeader(cabecera,valor);
        List<ReporteAnualDTO> listaGananciasA = reporteService.obtenerGananciasAnio();


        GananciasAExporterPDF exporter = new GananciasAExporterPDF(listaGananciasA);
        exporter.exportar(response);
    }


}
