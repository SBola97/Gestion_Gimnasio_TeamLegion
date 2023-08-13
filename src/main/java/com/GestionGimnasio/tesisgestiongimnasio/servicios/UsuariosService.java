package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.LoginDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.UsuarioMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.RolesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Service
public class UsuariosService implements iUsuariosService{

    @Autowired
    private UsuariosRepository usuariosRepository;

    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final int maxIntentos = 3;
    private final int minutosBloqueo = 15;

    @Override
    public UsuariosDTO ingresarUsuario(UsuariosDTO usuariosDTO) {
        int idPersona = usuariosDTO.getIdPersona();
        usuariosDTO.setContraseña(passwordEncoder.encode(usuariosDTO.getContraseña()));
        Usuarios usuarios = mapper.toUsuarios(usuariosDTO);

        Personas personaId = personasRepository.findById(idPersona)
                .orElseThrow(()-> new RuntimeException("No encontrado"));
        usuarios.setPersonas(personaId);

        if(usuariosDTO.getIdUsuario() == 0) {
            if (usuariosRepository.findUsuariosByPersonas_IdPersona(personaId.getIdPersona()).isPresent())
            {
                throw new RuntimeException("Suscriptor ya registrado como usuario");
            }
        }


        usuariosRepository.save(usuarios);

        return mapper.toUsuariosDTO(usuarios);
    }

    @Override
    public UsuariosDTO modificarUsuario(int idUsuario, UsuariosDTO usuariosDTO) {
        return null;
    }

    @Override
    public void eliminarUsuario(int idUsuario) {
        usuariosRepository.deleteById(idUsuario);
    }

    @Override
    public UsuariosDTO buscarUsuario(int idUsuario) {
        return mapper.toUsuariosDTO(usuariosRepository.findById(idUsuario)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado")));
    }

    @Override
    public List<UsuariosDTO> obtenerUsuarios() {

        return mapper.toUsuariosDTO((List<Usuarios>)usuariosRepository.findAll());
    }

    @Override
    public Page<Usuarios> listarUsuarios(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber -1,5);
        Page<Usuarios> usuarios = usuariosRepository.findAll(pageable);
        return usuarios;
    }

    @Override
    public Page<Usuarios> searchUsuarios(String keyword, int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber -1,5);
        return usuariosRepository.searchUsuariosByNameOrLastName(keyword,pageable);
    }


/*    public UsuariosDTO obtenerUserPersona(HttpServletRequest request, UsuariosDTO usuariosDTO)
    {
       String nombreu = request.getUserPrincipal().getName();
       Optional<Personas> personas = personasRepository.findPersonasByUsuariosNombreUsuario(nombreu);

       if(personas.isPresent())
       {
           usuariosDTO.setNombre(personas.get().getNombre());
           usuariosDTO.setApellidos(personas.get().getApellidos());
           usuariosDTO.setEmail(personas.get().getEmail());
       }
       return usuariosDTO;
    }*/
    @Override
    public UsuariosDTO resetPassword(String username, String password)
    {
        Usuarios user = usuariosRepository.findByNombreUsuario(username)
                .orElseThrow((()-> new RuntimeException("Usuario no encontrado")));

        user.setContraseña(passwordEncoder.encode(password));

        Usuarios nuser = usuariosRepository.save(user);
        return mapper.toUsuariosDTO(nuser);
    }

    public void verificarIntentos(LoginDTO loginDTO)
    {
        /*Integer contIntentos = (Integer) session.getAttribute("contIntentos");

        if(contIntentos == null)
        {
            contIntentos = 0;
        }*/
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken
                        (loginDTO.getUsername(), loginDTO.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //session.setAttribute("contIntentos",0);
            }
            catch (AuthenticationException e)
            {
                throw new RuntimeException("Intento de inicio de sesión fallido");
            }
    }

    public int getIntentosDisponibles(HttpSession session)
    {
        Integer contIntentos = (Integer) session.getAttribute("contIntentos");
        if(contIntentos == null)
        {
            return maxIntentos;
        }
        return maxIntentos - contIntentos;
    }

    public LocalDateTime getTime(HttpSession session)
    {
        LocalDateTime lockout = (LocalDateTime) session.getAttribute("lockout");
        return lockout;
    }

}
