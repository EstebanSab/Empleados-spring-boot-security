package com.empleados.empleadosApi.controller;




import com.empleados.empleadosApi.model.Empleado;
import com.empleados.empleadosApi.service.EmpleadoService;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
public class AuthenticationController {

    @Autowired
    private EmpleadoService empleadoService;


    @PostMapping("/login")
    public List<Empleado> signIn(HttpServletResponse response) {
        String nombre = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        return empleadoService.getEmpleadoByName(nombre);
    }

    @GetMapping("/empleado")
    public List<Empleado> empl(HttpServletRequest request) {
        String nombre = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return empleadoService.getEmpleadoByName(nombre);
    }    


    @PostMapping("/crearUsuario")
    public void signUp(@RequestBody Empleado empleado) {
        this.empleadoService.crearEmpleado(empleado);
        //return ResponseEntity.created(URI.create("/users/" + createdEmpleado.getId() + "/profile")).body(createdEmpleado);
    }

    @PostMapping("/desloguearse")
    public ResponseEntity<Void> signOut() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
    
}
