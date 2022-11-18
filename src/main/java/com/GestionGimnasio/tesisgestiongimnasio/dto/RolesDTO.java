package com.GestionGimnasio.tesisgestiongimnasio.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class RolesDTO {

    private int idRol;

    @NotEmpty(message = "El campo nombre es requerido")
    private String nombre;

}
