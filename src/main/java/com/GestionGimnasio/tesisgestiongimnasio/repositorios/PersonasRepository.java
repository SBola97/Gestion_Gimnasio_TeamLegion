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
    @Query(value = "SELECT * FROM personas INNER JOIN roles on personas.id_rol = roles.id_rol where roles.nombre='Cliente'", nativeQuery = true)
    Page<Personas> findClientes(Pageable pageable);

    @Query(value = "SELECT * FROM personas INNER JOIN roles on personas.id_rol = roles.id_rol where roles.nombre='Profesor'", nativeQuery = true)
    Page<Personas> findProfesores(Pageable pageable);

    @Query(value = "SELECT * FROM personas where personas.id_rol=2", nativeQuery = true)
    Page<Personas> getClientes(Pageable pageable);

    @Query(value = "SELECT * FROM personas where personas.id_rol=3", nativeQuery = true)
    Page<Personas> getProfesores(Pageable pageable);


    public int countPersonasByRolesNombre(String nombreRol);

    @Query(value = "SELECT * from personas as p inner join usuarios as u on p.id_persona = u.id_persona where u.nombre_usuario=:nombreU",nativeQuery = true)
    public Personas findPersonasByUsuariosNombreUsuario(String nombreU);

    @Query(value = "select * from personas as p left join inscripciones as i on i.id_persona = p.id_persona where i.id_persona is NULL",
            nativeQuery = true)
    public List<Personas> findPersonasNoInscritas();

    @Query(value="SELECT *  FROM personas as p left join usuarios as u  on u.id_persona = p.id_persona where u.id_persona is NULL",
    nativeQuery = true)
    public List<Personas> personasSinUser();

}
