package com.GestionGimnasio.tesisgestiongimnasio.servicios;


import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;

import java.util.List;

public interface iUsuariosService {

    UsuariosDTO ingresarUsuario(UsuariosDTO usuariosDTO);

    UsuariosDTO modificarUsuario(int idUsuario, UsuariosDTO usuariosDTO);

    void eliminarUsuario(int idUsuario);

    UsuariosDTO buscarUsuario(int idUsuario);

    List<UsuariosDTO> obtenerUsuarios();
}
