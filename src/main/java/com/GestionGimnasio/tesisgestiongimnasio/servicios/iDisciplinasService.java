package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.DisciplinasDTO;

import java.util.List;
public interface iDisciplinasService {

    DisciplinasDTO ingresarDisciplina(DisciplinasDTO disciplinasDTO);

    DisciplinasDTO modificarDisciplina(DisciplinasDTO disciplinasDTO);

    void eliminarDisciplina(int idDisciplina);

    DisciplinasDTO buscarDisciplina(int idDisciplina);

    List<DisciplinasDTO> obtenerDisciplinas();

}
