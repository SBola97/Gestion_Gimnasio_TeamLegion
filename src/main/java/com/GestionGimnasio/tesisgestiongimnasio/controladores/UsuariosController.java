package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.*;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.PersonasService;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gym/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private PersonasService personasService;

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


    @GetMapping("/listar/page/{pageNumber}")
    public String listarUsuarios(@PathVariable("pageNumber") int currentPage,Model modelo)
    {
        Page<Usuarios> page = usuariosService.listarUsuarios(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Usuarios> usuarios = page.getContent();
        modelo.addAttribute("listaUsuarios",usuarios);
        //modelo.addAttribute("listaUsuarios",usuariosService.obtenerUsuarios());
        modelo.addAttribute("currentPage",currentPage);
        modelo.addAttribute("totalPages",totalPages);
        modelo.addAttribute("totalItems",totalItems);
        return "usuarios";
    }

    @GetMapping("/formulario")
    public String mostrarFormularioUsuarios(Map<String,Object> modelo)
    {
        UsuariosDTO usuariosDTO = new UsuariosDTO();
        //List<PersonasDTO> listaPersonas = personasService.obtenerPersona();
        List<PersonasDTO> listaPersonas = personasService.obtenerPersonasSinUser();
        modelo.put("titulo","Registro de usuarios");
        modelo.put("listaPersonas",listaPersonas);
        modelo.put("usuarios",usuariosDTO);
        return "usuarios_form";
    }

    @PostMapping("/guardar")
    public String guardarUsuarios(@Valid @ModelAttribute("usuarios") UsuariosDTO usuariosDTO, BindingResult result,
                                  Model modelo, RedirectAttributes flash, SessionStatus status)
    {
        if(result.hasErrors())
        {
            modelo.addAttribute("titulo","Registro de usuarios");
            System.out.println("Errores:"+result.toString());
            return "usuarios_form";
        }
        String mensaje = "Usuario registrado con éxito";
        usuariosService.ingresarUsuario(usuariosDTO);
        modelo.addAttribute("usuarios",usuariosDTO);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/gym/usuarios/listar/page/1";
    }
    @GetMapping("/guardar/{idUsuario}")
    public String editarUsuario(@PathVariable(value = "idUsuario") int idU, Map<String,Object> modelo,RedirectAttributes flash)
    {
        UsuariosDTO usuariosDTO = null;
        List<PersonasDTO> listaPersonas = personasService.obtenerPersonasSinUser();

        if(idU>0) {
            usuariosDTO = usuariosService.buscarUsuario(idU);
            if (usuariosDTO == null) {
                flash.addFlashAttribute("error", "Usuario no encontrado en la Base de Datos");
                return "redirect:/gym/usuarios/listar";
            }
        }
        else
        {
            flash.addFlashAttribute("error","Id de usuario no puede ser 0");
            return "redirect:/gym/usuarios/listar";
        }
        //añadir a la lista de personas sin usuario el usuario ya registrado para poder editarlo
        listaPersonas.add(personasService.buscarPersona(usuariosDTO.getIdPersona()));
        modelo.put("titulo","Modificación de usuario");
        modelo.put("usuarios",usuariosDTO);
        modelo.put("listaPersonas",listaPersonas);
        return "usuarios_form";

    }
    @GetMapping("/eliminar/{idUsuario}")
    public String eliminarUsuario(@PathVariable(value ="idUsuario") int idU, RedirectAttributes flash)
    {
        if(idU>0)
        {
            usuariosService.eliminarUsuario(idU);
            flash.addFlashAttribute("success","Usuario eliminado con éxito");
        }
        return "redirect:/gym/usuarios/listar/page/1";
    }
}
