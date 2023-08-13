package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import org.springframework.data.domain.Page;

import java.util.List;

public interface iUsuariosService {

    UsuariosDTO ingresarUsuario(UsuariosDTO usuariosDTO);

    UsuariosDTO modificarUsuario(int idUsuario, UsuariosDTO usuariosDTO);

    void eliminarUsuario(int idUsuario);

    UsuariosDTO buscarUsuario(int idUsuario);

    List<UsuariosDTO> obtenerUsuarios();

    Page<Usuarios> listarUsuarios(int pageNumber);

    Page<Usuarios> searchUsuarios(String keyword, int pageNumber);

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
    UsuariosDTO resetPassword(String username, String password);
}
