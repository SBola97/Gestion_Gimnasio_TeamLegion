package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Competidores_Torneo")
public class Competidores_Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompetidorTorneo;
    @ManyToOne
    private Personas personas;

    @ManyToOne
    private Torneos torneos;
}
