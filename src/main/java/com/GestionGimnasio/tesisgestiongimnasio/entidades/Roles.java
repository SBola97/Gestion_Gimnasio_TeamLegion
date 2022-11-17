package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
public class Roles {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column(length=25,nullable = false,unique = true)
    private String nombre;

    @OneToOne(mappedBy = "roles")
    private Personas personas;
}
