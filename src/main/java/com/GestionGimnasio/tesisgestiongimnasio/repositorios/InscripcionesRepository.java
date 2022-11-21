package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades.Inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionesRepository extends JpaRepository<Inscripciones,Integer> {
}
