package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "Pagos")
@Getter
@Setter
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    @NotNull
    private LocalDate fechaPago;

    @NotNull
    @NotEmpty
    private String estadoPago;

    @OneToOne
    @JoinColumn(name = "idInscripcion",referencedColumnName = "idInscripcion")
    private Inscripciones inscripciones;

    @ManyToOne
    @JoinColumn(name = "idFormaPago",referencedColumnName = "idFormaPago")
    private FormaPago formaPago;

    private float valorp;

}
