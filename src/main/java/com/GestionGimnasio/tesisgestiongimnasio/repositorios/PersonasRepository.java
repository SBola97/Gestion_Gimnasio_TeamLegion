package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonasRepository extends JpaRepository<Personas,Integer> {
    @Query(value = "SELECT * FROM personas as p INNER JOIN roles as r on p.id_rol = r.id_rol where r.nombre=:nombre",
            nativeQuery = true)
    Page<Personas> findPersonasByRoles_Nombre(@Param("nombre")String nombreRol, Pageable pageable);

    public int countPersonasByRolesNombre(String nombreRol);

    @Query(value = "SELECT * from personas as p inner join usuarios as u on p.id_persona = u.id_persona where u.nombre_usuario=:nombreU",nativeQuery = true)
    public Personas findPersonasByUsuariosNombreUsuario(String nombreU);
}
