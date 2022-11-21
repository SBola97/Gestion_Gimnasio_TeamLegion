package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping

    public ResponseEntity<UsuariosDTO> ingresarUsuario(@RequestBody @Valid UsuariosDTO usuariosDTO)
    {
        return new ResponseEntity<>(usuariosService.ingresarUsuario(usuariosDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UsuariosDTO> getUsuarios(){
        return usuariosService.obtenerUsuarios();
    }

}
