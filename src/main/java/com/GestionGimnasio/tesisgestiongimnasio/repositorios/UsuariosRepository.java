package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
    @Query(value = "SELECT * " +
            "FROM usuarios as u INNER JOIN personas as p " +
            "INNER JOIN roles as r on u.id_persona = p.id_persona and p.id_rol = r.id_rol",nativeQuery = true)
    List<Usuarios> findUsuariosByPersonas_IdPersona();

    public Optional<Usuarios> findByNombreUsuario(String nombre);

    public Boolean existsByNombreUsuario(String nombre);
}
