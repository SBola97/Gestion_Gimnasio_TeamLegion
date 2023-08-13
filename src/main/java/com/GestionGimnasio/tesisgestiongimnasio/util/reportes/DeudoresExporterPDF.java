package com.GestionGimnasio.tesisgestiongimnasio.util.reportes;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ReporteDeudoresDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DeudoresExporterPDF {

    private List<ReporteDeudoresDTO> deudoresDTOList;

    private String mesNombre;

    private int anio;

    public DeudoresExporterPDF() {
    }

    public DeudoresExporterPDF(List<ReporteDeudoresDTO> deudoresDTOList) {
        this.deudoresDTOList = deudoresDTOList;
    }

    public DeudoresExporterPDF(List<ReporteDeudoresDTO> deudoresDTOList, String mesNombre, int anio) {
        this.deudoresDTOList = deudoresDTOList;
        this.mesNombre = mesNombre;
        this.anio = anio;
    }

    private void escribirCabeceraTabla(PdfPTable tabla)
    {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(new Color(12, 100, 214));
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Suscriptor",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha de Inicio",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha de Fin",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Valor pendiente ($)",fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
    }

    private void escribirDatosTabla(PdfPTable tabla)
    {
        for(ReporteDeudoresDTO deudoresDTO : deudoresDTOList)
        {
            PdfPCell celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(deudoresDTO.getNombrePersona()+' '+deudoresDTO.getApellidos()));
            tabla.addCell(celda);

            celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(String.valueOf(deudoresDTO.getFechaInicio())));
            tabla.addCell(celda);

            celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(String.valueOf(deudoresDTO.getFechaFin())));
            tabla.addCell(celda);

            celda = new PdfPCell();
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPhrase(new Phrase(String.valueOf(deudoresDTO.getValorp())));
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
        Paragraph titulo = new Paragraph("Reporte de Deudores: " + mesNombre + " - " + anio,fuente);
        line.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        //header.setAlignment(Paragraph.ALIGN_CENTER);
        //documento.add(header);
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
