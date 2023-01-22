package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class Competidores_Torneo_Key implements Serializable {

    //@Column(name="torneo_id")
    private Long torneo_id;
    //@Column(name="persona_id")
    private Long persona_id;

    public Competidores_Torneo_Key(Long torneos, Long personas) {
        this.torneo_id = torneos;
        this.persona_id = personas;
    }

    public Long getTorneo_id() {
        return torneo_id;
    }

    public void setTorneo_id(Long torneo_id) {
        this.torneo_id = torneo_id;
    }

    public Long getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(Long persona_id) {
        this.persona_id = persona_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competidores_Torneo_Key that = (Competidores_Torneo_Key) o;
        return Objects.equals(torneo_id, that.torneo_id) && Objects.equals(persona_id, that.persona_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(torneo_id, persona_id);
    }
}
