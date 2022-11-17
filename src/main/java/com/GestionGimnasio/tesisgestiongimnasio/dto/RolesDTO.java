package com.GestionGimnasio.tesisgestiongimnasio.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Data
@NoArgsConstructor
public class RolesDTO {

    private int idRol;

    private String nombre;

}
