package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.PagosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteAnualDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteDeudoresDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteGananciasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.InscripcionesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private PagosRepository pagosRepository;

    @Autowired
    private InscripcionesRepository inscripcionesRepository;

    public BigDecimal obtenerGanancias(ReporteGananciasDTO reporteGananciasDTO)
    {
        BigDecimal total = pagosRepository.sumValorpByFechaPago(reporteGananciasDTO.getMes());
        reporteGananciasDTO.setTotal(total);
        return reporteGananciasDTO.getTotal();
    }

    public List<ReporteAnualDTO> obtenerGananciasAnio()
    {
        List<Object> results = pagosRepository.findPagosAnuales();
        List<ReporteAnualDTO> reporteAnio = new ArrayList<>();
        int i = 0;

        Iterator itr = results.iterator();

        while(itr.hasNext()) {

            Object[] arrObj = (Object[])itr.next();

            ReporteAnualDTO e = new ReporteAnualDTO();

            for(Object obj:arrObj) {
                switch (i){
                    case 0:
                        e.setMes((String)obj);
                        break;
                    case 1:
                        e.setTotal((Double) obj);
                        break;
                }
                i++;
            }
            reporteAnio.add(e);
            i=0;
        }
        return reporteAnio;
    }

    public List<ReporteDeudoresDTO> obtenerDeudoresMes(ReporteDeudoresDTO reporteDeudoresDTO){
        List<Object> results = pagosRepository.findPagosByEstadoPagoAndFechaPago(reporteDeudoresDTO.getMes());
        List<ReporteDeudoresDTO> reporteDeudores = new ArrayList<>();
        int i = 0;

        Iterator itr = results.iterator();

        while(itr.hasNext()) {

            Object[] arrObj = (Object[])itr.next();

            ReporteDeudoresDTO e = new ReporteDeudoresDTO();

            for(Object obj:arrObj) {
                switch (i){
                    case 0:
                        e.setNombrePersona((String)obj);
                        break;
                    case 1:
                        e.setApellidos((String) obj);
                        break;
                    case 2:
                        e.setFechaInicio((Date) obj);
                        break;
                    case 3:
                        e.setFechaFin((Date) obj);
                        break;
                    case 4:
                        e.setValorp((float) obj);
                        break;
                }
                i++;
            }
            reporteDeudores.add(e);
            i=0;
        }
        return reporteDeudores;
    }


    public List<ReporteGananciasDTO> obtenerPagosMes(ReporteGananciasDTO reporteGananciasDTO){
        List<Object> resultados = pagosRepository.findPagosByFechaPago(reporteGananciasDTO.getMes());
        List<ReporteGananciasDTO> reporteGanancias = new ArrayList<>();

        int i = 0;

        Iterator itr = resultados.iterator();

        while(itr.hasNext()) {

            Object[] arrObj = (Object[])itr.next();

            ReporteGananciasDTO e = new ReporteGananciasDTO();

            for(Object obj:arrObj) {
                switch (i){
                    case 0:
                        e.setNombrePersona((String)obj);
                        break;
                    case 1:
                        e.setApellidos((String) obj);
                        break;
                    case 2:
                        e.setFecha((Date) obj);
                        break;
                    case 3:
                        e.setValorp((float) obj);
                        break;
                }
                i++;
            }
            reporteGanancias.add(e);
            i=0;
        }
        return reporteGanancias;
        }
    public String getMonthName(int mes) {
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        if (mes >= 1 && mes <= 12) {
            return months[mes - 1];
        }
        return "Desconocido";
    }

    }

