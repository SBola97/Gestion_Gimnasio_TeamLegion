package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.UsuarioMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.RolesRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService implements iUsuariosService{

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UsuariosDTO ingresarUsuario(UsuariosDTO usuariosDTO) {
        int idPersona = usuariosDTO.getIdPersona();
        usuariosDTO.setContraseña(passwordEncoder.encode(usuariosDTO.getContraseña()));
        Usuarios usuarios = mapper.toUsuarios(usuariosDTO);

        Personas personaId = personasRepository.findById(idPersona)
                .orElseThrow(()-> new RuntimeException("No encontrado"));
        usuarios.setPersonas(personaId);

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
}
