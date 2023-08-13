package com.GestionGimnasio.tesisgestiongimnasio.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String username;
    private String email;
    private String telefono;
    private String npassword;
}
