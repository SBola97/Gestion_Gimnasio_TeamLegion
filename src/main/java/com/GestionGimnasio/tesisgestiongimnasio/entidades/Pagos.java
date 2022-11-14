package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "Pagos")
@Data
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    @NotNull
    private LocalDate fechaPago;

    @NotEmpty
    private String estadoPago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idInscripcion",referencedColumnName = "idInscripcion")
    private Inscripciones inscripciones;

    @OneToOne(mappedBy = "pagos",cascade = CascadeType.ALL)
    private FormaPago formaPago;

}
