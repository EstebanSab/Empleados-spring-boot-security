package com.empleados.empleadosApi.controller;


import java.net.URI;
//import javax.validation.Valid;

import com.empleados.empleadosApi.model.Empleado;
import com.empleados.empleadosApi.security.UserAuthenticationProvider;
import com.empleados.empleadosApi.service.EmpleadoService;
import com.empleados.empleadosApi.dto.EmpleadoDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AuthenticationController {

    private  EmpleadoService empleadoService;
    private  UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public AuthenticationController(EmpleadoService empleadoService,
                                    UserAuthenticationProvider userAuthenticationProvider) {
        this.empleadoService = empleadoService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @PostMapping("/signIn")
    public ResponseEntity<EmpleadoDto> signIn(@AuthenticationPrincipal EmpleadoDto empleado) {
        empleado.setToken(userAuthenticationProvider.createToken(empleado.getLogin()));
        return ResponseEntity.ok(empleado);
    }

    @PostMapping("/signUp")
    public ResponseEntity<EmpleadoDto> signUp(@RequestBody @Valid SignUpDto empleado) {
        EmpleadoDto createdEmpleado = this.empleadoService.signUp(empleado);
        return ResponseEntity.created(URI.create("/users/" + createdEmpleado.getId() + "/profile")).body(createdEmpleado);
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDto user) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}