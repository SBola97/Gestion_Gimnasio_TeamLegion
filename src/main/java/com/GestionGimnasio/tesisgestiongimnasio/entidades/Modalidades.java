package com.GestionGimnasio.tesisgestiongimnasio.entidades;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Modalidades")
@Getter
@Setter
public class Modalidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModalidad;

    @NotEmpty
    private String tipo;

    @NotEmpty
    private String nombre;

    @NotNull
    private float valor;

    @OneToMany(mappedBy = "modalidades")

    private Set<Inscripciones> inscripciones;

}
