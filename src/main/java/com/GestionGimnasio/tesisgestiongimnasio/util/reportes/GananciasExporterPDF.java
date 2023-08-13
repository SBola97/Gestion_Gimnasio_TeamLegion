package com.GestionGimnasio.tesisgestiongimnasio.util.reportes;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteGananciasDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public class GananciasExporterPDF {

    private List<ReporteGananciasDTO> gananciasDTOList;

    private BigDecimal total;

    private String mesNombre;

    private int anio;

    public GananciasExporterPDF(List<ReporteGananciasDTO> gananciasDTOList, BigDecimal ganancias, String mes, int anio) {
        this.gananciasDTOList = gananciasDTOList;
        this.total = ganancias;
        this.mesNombre = mes;
        this.anio = anio;
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

        com.lowagie.text.Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Suscriptor",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha del Pago",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Valor ($)",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

    }

    private void escribirDatosTabla(PdfPTable tabla)
    {
        for(ReporteGananciasDTO gananciasDTO : gananciasDTOList)
        {
            PdfPCell celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(gananciasDTO.getNombrePersona() + ' ' + gananciasDTO.getApellidos()));
            tabla.addCell(celda);

            celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(gananciasDTO.getFecha().toString()));
            tabla.addCell(celda);

            celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(String.valueOf(gananciasDTO.getValorp())));
            tabla.addCell(celda);
        }
    }

    private void escribirTotal(PdfPTable tabla, BigDecimal total) {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(new Color(34,197 ,94));
        celda.setPadding(5);

        com.lowagie.text.Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("TOTAL ($)", fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);


        tabla.addCell("");

        com.lowagie.text.Font fuentet = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);

        celda = new PdfPCell(new Phrase(String.valueOf(total), fuentet));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
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
            logoImage.scaleToFit(100, 100); // Adjust the size of the logo as needed

            // Crear una instancia de PdfPTable y setear ancho
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

            // Add the logoTable to the document
            documento.add(logoTable);
        }
        else{
            System.out.printf("Imagen no encontrada");
        }

        //Paragraph header = new Paragraph("GIMNASIO 'TEAM LEGIÓN' RIOBAMBA", fuenteh);
        Paragraph line = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
        Paragraph titulo = new Paragraph("Reporte de Ganancias: " + mesNombre + " - " + anio,fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        line.setAlignment(Paragraph.ALIGN_CENTER);
        //header.setAlignment(Paragraph.ALIGN_CENTER);


        PdfPTable tablaTotal = new PdfPTable(3);
        tablaTotal.setWidthPercentage(100);
        tablaTotal.setWidths(new float[]{2f, 2f, 2f});
        tablaTotal.setWidthPercentage(110);

        escribirTotal(tablaTotal, total);

        //documento.add(header);
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
        documento.add(tablaTotal);
        documento.close();
    }
}
