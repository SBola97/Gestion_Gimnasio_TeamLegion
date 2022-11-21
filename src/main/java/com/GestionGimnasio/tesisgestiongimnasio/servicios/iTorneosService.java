package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.TorneosDTO;

import java.util.List;

public interface iTorneosService {

    TorneosDTO ingresarTorneo(TorneosDTO torneosDTO);

    TorneosDTO modificarTorneo(TorneosDTO torneosDTO);

    void eliminarTorneo(int idTorneo);

    TorneosDTO buscarTorneo(int idTorneo);

    List<TorneosDTO> obtenerTorneos();
}
