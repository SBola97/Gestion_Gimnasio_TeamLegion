package com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Competidores_Torneo")
@Data
@NoArgsConstructor
public class Competidores_Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompetidorTorneo;
    @ManyToOne
    private Personas personas;

    @ManyToOne
    private Torneos torneos;
}
