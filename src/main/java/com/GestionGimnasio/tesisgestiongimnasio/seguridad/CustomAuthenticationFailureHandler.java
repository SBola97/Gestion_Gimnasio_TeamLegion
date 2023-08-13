package com.GestionGimnasio.tesisgestiongimnasio.seguridad;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        HttpSession session = request.getSession();

        Integer contIntentos = (Integer) session.getAttribute("contIntentos");
        if (contIntentos == null) {
            contIntentos = 0;
        }

        session.setAttribute("contIntentos", contIntentos + 1);

        if(contIntentos + 1 >= 3)
        {
            LocalDateTime lockout = LocalDateTime.now().plusMinutes(15);
            session.setAttribute("lockout",lockout);
        }

        // Redirect the user back to the login page with an error parameter
        response.sendRedirect("/login?error=true");
    }
}
