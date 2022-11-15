package com.GestionGimnasio.tesisgestiongimnasio.controladores;


import com.GestionGimnasio.tesisgestiongimnasio.dto.RolesDTO;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym/roles")
@CrossOrigin(origins = "*")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @PostMapping
    public ResponseEntity<RolesDTO> ingresarRol(@RequestBody @Valid RolesDTO rolesDTO)
    {
        return new ResponseEntity<>(rolesService.ingresarRol(rolesDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<RolesDTO> getRoles()
    {
        return rolesService.obtenerRoles();
    }


}
