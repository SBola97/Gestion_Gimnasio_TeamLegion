package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonasRepository extends JpaRepository<Personas,Integer> {
    @Query(value = "SELECT * FROM personas as p INNER JOIN roles as r on p.id_rol = r.id_rol where r.nombre=:nombre",
            nativeQuery = true)
    List<Personas> findPersonasByRoles_Nombre(@Param("nombre")String nombreRol);

    public int countPersonasByRolesNombre(String nombreRol);
}
