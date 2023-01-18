package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class Competidores_Torneo_Key implements Serializable {
    @Column(name="idPersona")
    private int idPersona;

    @Column(name="idTorneo")
    private int idTorneo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competidores_Torneo_Key that = (Competidores_Torneo_Key) o;
        return idPersona == that.idPersona && idTorneo == that.idTorneo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona, idTorneo);
    }
}
