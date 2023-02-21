package com.GestionGimnasio.tesisgestiongimnasio.util.reportes;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteAnualDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class GananciasAExporterPDF {

    private List<ReporteAnualDTO> reporteAnualDTOList;

    public GananciasAExporterPDF(List<ReporteAnualDTO> reporteAnualDTOList) {
        this.reporteAnualDTOList = reporteAnualDTOList;
    }

    private void escribirCabeceraTabla(PdfPTable tabla)
    {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(new Color(12, 100, 214));
        celda.setPadding(5);

        com.lowagie.text.Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Mes",fuente));
        tabla.addCell(celda);


        celda.setPhrase(new Phrase("Total ($)",fuente));
        tabla.addCell(celda);
    }

    private void escribirDatosTabla(PdfPTable tabla)
    {
        for(ReporteAnualDTO reporteAnualDTO : reporteAnualDTOList)
        {
            tabla.addCell(String.valueOf(reporteAnualDTO.getMes()));
            tabla.addCell(String.valueOf(reporteAnualDTO.getTotal()));
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
        Paragraph titulo = new Paragraph("Lista de ganancias anuales",fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        line.setAlignment(Paragraph.ALIGN_CENTER);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(header);
        documento.add(line);
        documento.add(titulo);
        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{2f,2f});
        tabla.setWidthPercentage(110);

        escribirCabeceraTabla(tabla);
        escribirDatosTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
}
