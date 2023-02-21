package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Competidores_Torneo_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Competidores_TorneoRepository extends JpaRepository<Competidores_Torneo, Integer> {
    @Query(value = "SELECT * FROM competidores_torneo AS ct INNER JOIN torneos " +
            "AS t ON ct.id_torneo = t.id_torneo INNER JOIN disciplinas AS d ON t.id_disciplina = d.id_disciplina " +
            "WHERE ct.id_persona =:idPersona LIMIT 3", nativeQuery = true)
    public List<Competidores_Torneo> findCompetidores_TorneoByPersonasIdPersona(@Param("idPersona") int idPersona);

    @Query(value = "SELECT count(DISTINCT ct.id_persona) from competidores_torneo as ct ",nativeQuery = true)
    public int countCompetidores();
}
