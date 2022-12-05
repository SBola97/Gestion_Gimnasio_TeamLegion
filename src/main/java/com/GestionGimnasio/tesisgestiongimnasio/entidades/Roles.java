package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Roles")
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column(unique = true)
    private String nombre;

    @OneToMany(mappedBy = "roles")
    private Set<Personas> personas;
}
