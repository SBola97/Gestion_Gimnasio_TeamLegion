package com.GestionGimnasio.tesisgestiongimnasio.entidades;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "Modalidades")
@Data
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

    @OneToOne()
    @JoinColumn(name = "idInscripcion",referencedColumnName = "idInscripcion")
    private Inscripciones inscripciones;

}
