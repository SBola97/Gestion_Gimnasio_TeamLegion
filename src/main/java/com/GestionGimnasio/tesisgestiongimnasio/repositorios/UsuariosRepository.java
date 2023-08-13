package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.UsuariosDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
    @Query(value = "SELECT * " +
            "FROM usuarios as u INNER JOIN personas as p " +
            "INNER JOIN roles as r on u.id_persona = p.id_persona and p.id_rol = r.id_rol",nativeQuery = true)
    List<Usuarios> findUsuariosByPersonas();

    public Optional<Usuarios> findByNombreUsuario(String nombre);

    @Query(value = "SELECT * FROM usuarios as u inner join personas as p on u.id_persona = p.id_persona" +
            " where p.telefono =:telefono", nativeQuery = true)
    public Optional<Usuarios> findByTelefonoUsuario(String telefono);

    @Query(value = "SELECT * FROM usuarios as u inner join personas as p on u.id_persona = p.id_persona" +
            " where p.email =:email", nativeQuery = true)
    public Optional<Usuarios> findByEmailUsuario(String email);

    public Boolean existsByNombreUsuario(String nombre);

    public Optional<Usuarios> findUsuariosByPersonas_IdPersona(int id);

    @Query(value ="SELECT * FROM usuarios as u inner join personas as p on u.id_persona = p.id_persona where (concat(p.nombre, ' ', p.apellidos) like %:keyword%)", nativeQuery = true)
    Page<Usuarios> searchUsuariosByNameOrLastName(String keyword, Pageable page);

}
