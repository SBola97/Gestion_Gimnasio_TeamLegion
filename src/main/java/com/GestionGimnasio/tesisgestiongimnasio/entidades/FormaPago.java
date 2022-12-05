package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Entity
@Table(name = "FormaPago")
@Data
public class FormaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormaPago;

    @NotEmpty
    private String nombre;

    @OneToMany(mappedBy = "formaPago",cascade = CascadeType.ALL)
    private Set<Pagos> pagos;
}
