package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Competidores_Torneo")
@Getter
@Setter
public class Competidores_Torneo {
    @EmbeddedId
    Competidores_Torneo_Key id = new Competidores_Torneo_Key();
    @ManyToOne
    @MapsId("idPersona")
    @JoinColumn(name="idPersona")
    private Personas personas;

    @ManyToOne
    @MapsId("idTorneo")
    @JoinColumn(name="idTorneo")
    private Torneos torneos;

    private String categoriaPeso;
}
