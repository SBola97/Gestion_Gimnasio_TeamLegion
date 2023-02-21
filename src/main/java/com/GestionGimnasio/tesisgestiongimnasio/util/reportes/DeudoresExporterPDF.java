package com.GestionGimnasio.tesisgestiongimnasio.util.reportes;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteDeudoresDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class DeudoresExporterPDF {

    private List<ReporteDeudoresDTO> deudoresDTOList;

    public DeudoresExporterPDF(List<ReporteDeudoresDTO> deudoresDTOList) {
        this.deudoresDTOList = deudoresDTOList;
    }


    private void escribirCabeceraTabla(PdfPTable tabla)
    {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(new Color(12, 100, 214));
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Suscriptor",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha de Inicio",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha de Fin",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Valor pendiente",fuente));
        tabla.addCell(celda);
    }

    private void escribirDatosTabla(PdfPTable tabla)
    {
        for(ReporteDeudoresDTO deudoresDTO : deudoresDTOList)
        {
            tabla.addCell(deudoresDTO.getNombrePersona()+' '+deudoresDTO.getApellidos());
            tabla.addCell(String.valueOf(deudoresDTO.getFechaInicio()));
            tabla.addCell(String.valueOf(deudoresDTO.getFechaFin()));
            tabla.addCell(String.valueOf(deudoresDTO.getValorp()));
        }
    }

    public void exportar(HttpServletResponse response) throws IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento,response.getOutputStream());
        documento.open();
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        Font fuenteh = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuenteh.setColor(Color.BLACK);
        fuente.setSize(16);
        fuenteh.setSize(20);
        Paragraph header = new Paragraph("GIMNASIO 'TEAM LEGIÓN' RIOBAMBA", fuenteh);
        Paragraph line = new Paragraph("--------------------------------------------------------------------");
        Paragraph titulo = new Paragraph("Listado de deudores",fuente);
        line.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(header);
        documento.add(line);
        documento.add(titulo);
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{2f,2f,2f,2f});
        tabla.setWidthPercentage(110);

        escribirCabeceraTabla(tabla);
        escribirDatosTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
}
