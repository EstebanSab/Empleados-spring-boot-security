package com.empleados.empleadosApi.service;


import com.empleados.empleadosApi.dto.CredentialsDto;

//import java.nio.CharBuffer;

import com.empleados.empleadosApi.dto.EmpleadoDto;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public EmpleadoDto authenticate(CredentialsDto credentialsDto) {
            return new EmpleadoDto(1L, "Sergio", "Lema", "login", "token");
    }

    public EmpleadoDto findByLogin(String login) {
        if ("login".equals(login)) {
            return new EmpleadoDto(1L, "Sergio", "Lema", "login", "token");
        }
        throw new RuntimeException("Invalid login");
    }
}
