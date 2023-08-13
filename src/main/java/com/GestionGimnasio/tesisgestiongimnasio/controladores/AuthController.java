package com.GestionGimnasio.tesisgestiongimnasio.controladores;

import com.GestionGimnasio.tesisgestiongimnasio.dto.LoginDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ResetPasswordDTO;
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
import java.security.SecureRandom;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

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
    public String login(Model model, HttpSession session)
    {
        int cont = usuariosService.getIntentosDisponibles(session);
        LocalDateTime lockout = usuariosService.getTime(session);

        if(lockout != null && LocalDateTime.now().isAfter(lockout))
        {
            session.setAttribute("contIntentos",0);
            session.removeAttribute("lockout");
        }
        
        String lockoutf = lockout != null ? lockout.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) : null;

        System.out.println("Intentos disponibles:"+ cont);
        System.out.println("Tiempo penalizado:"+lockout);
        System.out.println("Hora actual: "+LocalDateTime.now());

        model.addAttribute("lockout",lockoutf);
        model.addAttribute("contIntentos",cont);
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
    public String autenticar(@Valid LoginDTO loginDTO, BindingResult result, Model modelo, HttpSession session){
        if(result.hasErrors())
        {
            return "Error de logueo";
        }
        usuariosService.verificarIntentos(loginDTO);
        int intentos = usuariosService.getIntentosDisponibles(session);
        modelo.addAttribute("intentos",intentos);
            /*Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);*/
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

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model modelo)
    {
        UsuariosDTO usuariosDTO = new UsuariosDTO();
        modelo.addAttribute("resetPassword", usuariosDTO);
        return "forgotPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@Valid @ModelAttribute("resetPassword") UsuariosDTO usuariosDTO, Model modelo)
    {
        try {
            modelo.addAttribute("resetPassword", usuariosDTO);
            usuariosService.resetPassword(usuariosDTO.getNombreUsuario(), usuariosDTO.getContraseña());
            return "redirect:/login";
        }
        catch(RuntimeException e)
        {
            return "usuarioNoEncontrado";
        }
    }
}
