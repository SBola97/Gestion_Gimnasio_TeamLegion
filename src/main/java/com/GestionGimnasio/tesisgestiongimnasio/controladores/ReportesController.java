package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gym/reportes")
@CrossOrigin(origins = "*")
public class ReportesController {

    @GetMapping()
    public String menuReportes()
    {
        return "reportes";
    }
}
