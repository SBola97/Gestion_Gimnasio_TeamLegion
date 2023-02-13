package com.GestionGimnasio.tesisgestiongimnasio.excepciones;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class gymExceptions {

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, SQLException.class, cedulaException.class
    , HibernateException.class})
    public String handleError(Exception ex, Model modelo)
    {
        modelo.addAttribute("message",ex.getLocalizedMessage());
        return "error";
    }

}


