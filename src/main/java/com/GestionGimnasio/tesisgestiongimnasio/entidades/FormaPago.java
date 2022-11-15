package com.GestionGimnasio.tesisgestiongimnasio.entidades;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "FormaPago")
@Data
public class FormaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormaPago;

    @NotEmpty
    private String nombre;

    @OneToOne(mappedBy = "formaPago",cascade = CascadeType.ALL)
    private Pagos pagos;
}
