package com.GestionGimnasio.tesisgestiongimnasio.servicios;

import com.GestionGimnasio.tesisgestiongimnasio.dto.ClienteDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.PersonasDTO;
import com.GestionGimnasio.tesisgestiongimnasio.dto.ProfesorDTO;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Personas;
import com.GestionGimnasio.tesisgestiongimnasio.entidades.Roles;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.ClienteMapper;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.PersonasMapper;
import com.GestionGimnasio.tesisgestiongimnasio.mappers.ProfesorMapper;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.PersonasRepository;
import com.GestionGimnasio.tesisgestiongimnasio.repositorios.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonasService implements iPersonasService {

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PersonasMapper mapper;

    @Autowired
    private ProfesorMapper mapperp;
    @Autowired
    private ClienteMapper mapperc;

    @Override
    public PersonasDTO ingresarPersona(PersonasDTO personasDTO)
    {

        int idRol = personasDTO.getIdRol();
        Personas personas = mapper.toPersonas(personasDTO);

        Roles rolesR = rolesRepository.findById(idRol).orElseThrow(()-> new RuntimeException("No encontrado"));

        personas.setRoles(rolesR);
        if(validadorDeCedula(personas.getCedula())) {
            personasRepository.save(personas);
            return mapper.toPersonasDTO(personas);
        }
        else
            throw new RuntimeException("Cédula no válida");
    }

    @Override
    public PersonasDTO modificarPersona(int idPersona, PersonasDTO personasDTO) {
        return null;
    }

    @Override
    public void eliminarPersona(int idPersona) {
        personasRepository.deleteById(idPersona);
    }

    @Override
    public PersonasDTO buscarPersona(int idPersona) {
        return mapper.toPersonasDTO(personasRepository.findById(idPersona)
                .orElseThrow(()-> new RuntimeException("Persona no encontrada")));
    }

    @Override
    public List<PersonasDTO> obtenerPersona() {
        return mapper.toPersonasDTO((List<Personas>)personasRepository.findAll());
    }


    @Override
    public List<ClienteDTO> obtenerClientes() {
        return mapperc.toclienteDTO((List<Personas>)personasRepository.findPersonasByRoles_Nombre("Cliente"));
    }

    @Override
    public List<ProfesorDTO> obtenerProfesores() {
        return mapperp.toprofesorDTO((List<Personas>)personasRepository.findPersonasByRoles_Nombre("Profesor"));
    }

    static boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;
        try {
            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    }
                    else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepción ocurrió en el proceso de validación");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

}
