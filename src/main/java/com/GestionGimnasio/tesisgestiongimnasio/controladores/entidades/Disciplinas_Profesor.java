package com.GestionGimnasio.tesisgestiongimnasio.controladores.entidades;

import lombok.Data;

import javax.persistence.*;

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
