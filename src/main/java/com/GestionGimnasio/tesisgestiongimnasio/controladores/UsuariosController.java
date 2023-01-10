package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/gym/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<UsuariosDTO> ingresarUsuario(@RequestBody @Valid UsuariosDTO usuariosDTO)
    {
        return new ResponseEntity<>(usuariosService.ingresarUsuario(usuariosDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public List<UsuariosDTO> getUsuarios(){
        return usuariosService.obtenerUsuarios();
    }


    @GetMapping("/listar")
    public String listarUsuarios(Model modelo)
    {
        modelo.addAttribute("listaUsuarios",usuariosService.obtenerUsuarios());
        return "usuarios";
    }

}
