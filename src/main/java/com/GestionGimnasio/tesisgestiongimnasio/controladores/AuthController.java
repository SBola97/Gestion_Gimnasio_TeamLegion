package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.LoginDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.UsuarioMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.*;
import com.GestionGimnasio.tesisgestiongimnasio.servicios.InscripcionesService;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@CrossOrigin(origins = "*")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UsuariosRepository usuariosRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    InscripcionesRepository inscripcionesRepository;

    @Autowired
    Competidores_TorneoRepository competidores_torneoRepository;

    @Autowired
    PersonasRepository personasRepository;

    @Autowired
    PagosRepository pagosRepository;
    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private InscripcionesService inscripcionesService;

    @Autowired
    UsuarioMapper mapper;
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
    public String index(Model modelo)
    {
        int day = LocalDate.now().getDayOfMonth();
        BigDecimal diario = pagosRepository.sumValorpByDia(day);
        int numCompetidores = competidores_torneoRepository.countCompetidores();
        modelo.addAttribute("gananciaDiaria",diario);
        modelo.addAttribute("numCompetidores",numCompetidores);
        modelo.addAttribute("mensualidadesVencidas",inscripcionesRepository.countMensualidadesPorVencer());
        modelo.addAttribute("numeroClientes",personasRepository.countPersonasByRolesNombre("Cliente"));
        return "index";
    }

    @GetMapping("/fichaPersonal")
    public String fichaP(Model modelo,HttpServletRequest request)
    {
        String name = request.getUserPrincipal().getName();
        modelo.addAttribute("usuario",name);
        modelo.addAttribute("personas",personasRepository.findPersonasByUsuariosNombreUsuario(name));
        return "fichaPersonal";
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
