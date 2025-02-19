package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Inscripciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InscripcionesRepository extends JpaRepository<Inscripciones,Integer> {
    @Query(value="SELECT count(datediff(i.fecha_fin,CURRENT_DATE)) from inscripciones as i where " +
            "datediff(i.fecha_fin,CURRENT_DATE) <= 5 and datediff(i.fecha_fin,CURRENT_DATE) > 0 and i.id_modalidad <> 3",nativeQuery = true)
    public int countMensualidadesPorVencer(); // número de mensualidades por vencer

    @Query(value="SELECT * from inscripciones as i " +
            "inner join personas as p on i.id_persona = p.id_persona where datediff(i.fecha_fin,CURRENT_DATE) <= 5 " +
            "and datediff(i.fecha_fin,CURRENT_DATE) > 0 and i.id_modalidad <> 3", nativeQuery = true)
    public Page<Inscripciones> findInscripcionesByFechaFin(Pageable pageable);

/*    @Query(value="SELECT datediff(:fecha,CURRENT_DATE) from inscripciones as i " +
            "where datediff(:fecha,CURRENT_DATE) <= 0",nativeQuery = true)
    public int verificarInscripciones(@Param("fecha") LocalDate fecha);*/
    public Optional<Inscripciones> findInscripcionesByPersonas_IdPersona(int idP);


    @Query(value="select * from inscripciones as i left join pagos as p  on p.id_inscripcion = i.id_inscripcion where p.id_inscripcion is NULL",
            nativeQuery = true)
    public List<Inscripciones> findInscripcionesNoPagadas();


    @Query(value = "SELECT * from inscripciones as i inner join personas as p on i.id_persona = p.id_persona where (concat(p.nombre, ' ', p.apellidos) like %:keyword%)", nativeQuery = true)
    Page<Inscripciones> searchSuscripcionesByNameOrLastName(String keyword, Pageable pageable);
}
