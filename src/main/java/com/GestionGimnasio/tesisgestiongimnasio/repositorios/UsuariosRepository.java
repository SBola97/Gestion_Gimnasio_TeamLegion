package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
}
