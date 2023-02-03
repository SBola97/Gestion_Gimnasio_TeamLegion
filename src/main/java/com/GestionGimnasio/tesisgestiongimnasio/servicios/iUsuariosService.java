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
}
