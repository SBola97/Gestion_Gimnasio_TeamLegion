package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Disciplinas_Profesor")
@Data
public class Disciplinas_Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDisciplinaProfesor;

    @ManyToOne
    private Personas personas;

    @ManyToOne
    private Disciplinas disciplinas;


}
