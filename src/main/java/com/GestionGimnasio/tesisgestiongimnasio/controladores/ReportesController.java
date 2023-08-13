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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
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
                            Model modelo ,BindingResult result, HttpSession session) {
        List<ReporteDeudoresDTO> listaDeudores = reporteService.obtenerDeudoresMes(reporteDeudoresDTO);
        if(result.hasErrors())
        {
            System.out.println("Errores: "+result.toString());
            return "reportes";
        }

        modelo.addAttribute("reporteDeudores",reporteDeudoresDTO);
        modelo.addAttribute("listaDeudores",listaDeudores);

        session.setAttribute("selectedMonth",reporteDeudoresDTO.getMes());

        return "reporteDeudores";
    }


    @PostMapping("/mes")
    public String buscarMesG(@Valid @ModelAttribute("reporteGanacias") ReporteGananciasDTO reporteGananciasDTO,
                             Model modelo , BindingResult result, HttpSession session)
    {
        if(result.hasErrors())
        {
            System.out.println("Errores: "+result.toString());
            return "reportes";
        }
        modelo.addAttribute("reporteGanancias",reporteGananciasDTO);
        modelo.addAttribute("listaPagosM",reporteService.obtenerPagosMes(reporteGananciasDTO));
        reporteService.obtenerGanancias(reporteGananciasDTO);

        session.setAttribute("selectedMonth",reporteGananciasDTO.getMes());

        return "reporteGananciasM";
    }

    @GetMapping("/pdf")
    public void exportarDeudores(HttpServletResponse response, ReporteDeudoresDTO deudoresDTO, HttpSession session)
            throws IOException {

        Integer selectedMonth = (Integer) session.getAttribute("selectedMonth");

        if(selectedMonth != null) {
            response.setContentType("application/pdf");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            //String fechaactual = dateFormat.format(new Date());
            String mes = reporteService.getMonthName(selectedMonth);
            int anio = LocalDate.now().getYear();
            String cabecera = "Content-Disposition";

            String valor = "attachment; filename=Deudores_" + mes + "_" + anio + ".pdf";

            response.setHeader(cabecera, valor);
            deudoresDTO.setMes(selectedMonth);
            List<ReporteDeudoresDTO> listaDeudores = reporteService.obtenerDeudoresMes(deudoresDTO);
            DeudoresExporterPDF exporter = new DeudoresExporterPDF(listaDeudores,mes,anio);
            exporter.exportar(response);
        }
        else{
            deudoresDTO.setMes(LocalDate.now().getMonth().getValue());
        }
    }

    @GetMapping("/pdfg")
    public void exportarGanancias(HttpServletResponse response, ReporteGananciasDTO reporteGananciasDTO,HttpSession session)
            throws IOException {

        Integer selectedMonth = (Integer) session.getAttribute("selectedMonth");

        if(selectedMonth != null) {
            response.setContentType("application/pdf");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String mes = reporteService.getMonthName(selectedMonth);
            int anio = LocalDate.now().getYear();

            String cabecera = "Content-Disposition";

            String valor = "attachment; filename=GananciasMensuales_" + mes + "_" + anio + ".pdf";

            response.setHeader(cabecera, valor);
            reporteGananciasDTO.setMes(selectedMonth);
            BigDecimal ganancias = reporteService.obtenerGanancias(reporteGananciasDTO);
            reporteGananciasDTO.setTotal(ganancias);
            System.out.println("Ganancias totales: "+ ganancias);
            List<ReporteGananciasDTO> listaGanancias = reporteService.obtenerPagosMes(reporteGananciasDTO);

            GananciasExporterPDF exporter = new GananciasExporterPDF(listaGanancias, ganancias, mes, anio);
            exporter.exportar(response);
        }
        else{
            reporteGananciasDTO.setMes(LocalDate.now().getMonth().getValue());
        }
    }

    @GetMapping("/pdfa")
    public void exportarGananciasA(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        int anio = LocalDate.now().getYear();

        String cabecera = "Content-Disposition";

        String valor = "attachment; filename=GananciasAnuales_" + anio + ".pdf";

        response.setHeader(cabecera,valor);
        List<ReporteAnualDTO> listaGananciasA = reporteService.obtenerGananciasAnio();


        GananciasAExporterPDF exporter = new GananciasAExporterPDF(listaGananciasA, anio);
        exporter.exportar(response);
    }


}
