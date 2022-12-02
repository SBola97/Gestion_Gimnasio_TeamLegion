package com.GestionGimnasio.tesisgestiongimnasio.excepciones;
import org.springframework.http.HttpStatus;
public class gymexceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus estado;
    private String mensaje;

    public gymexceptions(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public gymexceptions(HttpStatus estado, String mensaje, String mensajeAlt) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensajeAlt;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}


