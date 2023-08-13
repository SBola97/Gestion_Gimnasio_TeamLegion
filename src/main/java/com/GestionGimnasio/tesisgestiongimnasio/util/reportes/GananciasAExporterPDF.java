package com.GestionGimnasio.tesisgestiongimnasio.util.reportes;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteAnualDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GananciasAExporterPDF {

    private List<ReporteAnualDTO> reporteAnualDTOList;

    private int anio;

    public GananciasAExporterPDF(List<ReporteAnualDTO> reporteAnualDTOList) {
        this.reporteAnualDTOList = reporteAnualDTOList;
    }

    public GananciasAExporterPDF() {
    }

    public GananciasAExporterPDF(List<ReporteAnualDTO> reporteAnualDTOList, int anio) {
        this.reporteAnualDTOList = reporteAnualDTOList;
        this.anio = anio;
    }

    private void escribirCabeceraTabla(PdfPTable tabla)
    {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(new Color(12, 100, 214));
        celda.setPadding(5);

        com.lowagie.text.Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Mes",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);


        celda.setPhrase(new Phrase("Total ($)",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
    }

    private void escribirDatosTabla(PdfPTable tabla)
    {
        for(ReporteAnualDTO reporteAnualDTO : reporteAnualDTOList)
        {
            PdfPCell celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(reporteAnualDTO.getMes()));
            tabla.addCell(celda);

            celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(String.valueOf(reporteAnualDTO.getTotal())));
            tabla.addCell(celda);
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

        String logoPath = "static/svgs/logo-gymlegion-nbg.png";
        // Load the company logo image from the resources folder
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(logoPath);
        if (inputStream != null)
        {
            Image logoImage = Image.getInstance(ImageIO.read(inputStream), null);
            logoImage.scaleToFit(100, 100);

            // Crear una instancia de PdfPTable y asignar ancho
            PdfPTable logoTable = new PdfPTable(2);
            logoTable.setWidthPercentage(100);
            logoTable.setWidths(new float[]{1f, 5f});

            // Añadir el logo a la 1ra celda
            PdfPCell logoCell = new PdfPCell(logoImage, true);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            logoCell.setPaddingLeft(10f);
            logoTable.addCell(logoCell);

            PdfPCell titleCell = new PdfPCell(new Phrase("ACADEMIA DE ARTES MARCIALES \n'TEAM LEGIÓN' RIOBAMBA", fuenteh));
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setPaddingLeft(10f);
            logoTable.addCell(titleCell);

            // Añadir tabla del Logo al documento
            documento.add(logoTable);
        }
        else{
            System.out.printf("Imagen no encontrada");
        }

        //Paragraph header = new Paragraph("GIMNASIO 'TEAM LEGIÓN' RIOBAMBA", fuenteh);
        Paragraph line = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
        Paragraph titulo = new Paragraph("Reporte de ganancias anuales: " + anio,fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        line.setAlignment(Paragraph.ALIGN_CENTER);
        //header.setAlignment(Paragraph.ALIGN_CENTER);
        //documento.add(header);
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
