package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.LoginDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.RolesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.UsuariosRepository;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "*")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UsuariosRepository usuariosRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsuariosRepository usuariosRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuariosDTO> registrar(@RequestBody @Valid UsuariosDTO usuariosDTO)
    {
        return new ResponseEntity<>(usuariosService.ingresarUsuario(usuariosDTO), HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public String login()
    {
        /*
        LoginDTO loginDTO = new LoginDTO();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDTO.getUsername(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);*/

        return "login_gym";
    }

    @GetMapping("/index")
    public String index()
    {
        return "index";
    }


    @PostMapping("/autenticar")
    public String autenticar(@Valid LoginDTO loginDTO, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "Error de logueo";
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDTO.getUsername(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "index";
    }

    @GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }


}
