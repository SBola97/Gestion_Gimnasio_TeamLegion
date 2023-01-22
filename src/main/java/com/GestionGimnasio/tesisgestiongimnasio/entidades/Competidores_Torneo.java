package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Competidores_Torneo")
public class Competidores_Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompetidorTorneo;

    @ManyToOne
    //@MapsId("idPersona")
    @JoinColumn(name="idPersona",referencedColumnName = "idPersona")
    private Personas personas;
    @ManyToOne
    //@MapsId("idTorneo")
    @JoinColumn(name="idTorneo",referencedColumnName = "idTorneo")
    private Torneos torneos;

    private float pesoc;
    private String categoriaPeso;
    public Competidores_Torneo(Torneos torneos, Personas personas, String categoriaPeso) {
        this.torneos = torneos;
        this.personas = personas;
        this.categoriaPeso = categoriaPeso;
    }

/*    public void setCompetidores_Torneo_Key(Competidores_Torneo_Key competidores_torneo_key)
    {
        this.torneo_id.setIdTorneo(Math.toIntExact(competidores_torneo_key.getTorneo_id()));
        this.persona_id.setIdPersona(Math.toIntExact(competidores_torneo_key.getPersona_id()));
    }*/

    public Competidores_Torneo() {}
}
