package com.GestionGimnasio.tesisgestiongimnasio.util.reportes;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteGananciasDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class GananciasExporterPDF {

    private List<ReporteGananciasDTO> gananciasDTOList;


    public GananciasExporterPDF(List<ReporteGananciasDTO> gananciasDTOList, BigDecimal ganancias) {
        this.gananciasDTOList = gananciasDTOList;
    }

    public GananciasExporterPDF(List<ReporteGananciasDTO> gananciasDTOList) {
        this.gananciasDTOList = gananciasDTOList;
    }

    public GananciasExporterPDF() {
    }

    private void escribirCabeceraTabla(PdfPTable tabla)
    {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(new Color(12, 100, 214));
        celda.setPadding(5);

        com.lowagie.text.Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Suscriptor",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha del Pago",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Valor",fuente));
        tabla.addCell(celda);

    }

    private void escribirDatosTabla(PdfPTable tabla)
    {
        for(ReporteGananciasDTO gananciasDTO : gananciasDTOList)
        {
            tabla.addCell(gananciasDTO.getNombrePersona()+' '+gananciasDTO.getApellidos());
            tabla.addCell(String.valueOf(gananciasDTO.getFecha()));
            tabla.addCell(String.valueOf(gananciasDTO.getValorp()));
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
        Paragraph titulo = new Paragraph("Listado de ganancias mensual ",fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        line.setAlignment(Paragraph.ALIGN_CENTER);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(header);
        documento.add(line);
        documento.add(titulo);
        PdfPTable tabla = new PdfPTable(3);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{2f,2f,2f});
        tabla.setWidthPercentage(110);

        escribirCabeceraTabla(tabla);
        escribirDatosTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
}
