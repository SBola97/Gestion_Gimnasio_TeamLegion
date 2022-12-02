package com.GestionGimnasio.tesisgestiongimnasio.repositorios;


import com.GestionGimnasio.tesisgestiongimnasio.entidades.Modalidades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModalidadesRepository extends JpaRepository<Modalidades,Integer> {

}
