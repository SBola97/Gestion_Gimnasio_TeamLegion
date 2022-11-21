package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Integer>{
    public Optional<Roles> findByNombre(String nombre);
}
