package com.empleados.empleadosApi.controller;

import com.empleados.empleadosApi.model.Empleado;
import com.empleados.empleadosApi.service.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("api/empleados")
public class EmpleadoController {

    private EmpleadoService empleadoService;  

    @Autowired
    public EmpleadoController(
        EmpleadoService empleadoService
        ){
        this.empleadoService=empleadoService;
    }
                  

    @GetMapping
    @PreAuthorize("hasAuthority('empleado:read')")
    public List<Empleado> getAllUsuarios() {

        return this.empleadoService.getAllEmpleados();
    }

    @GetMapping("/empleado/{id}")
     public Empleado getOneUsuario(@PathVariable Long id) {
        return this.empleadoService.getEmpleadoById(id);
    }


    @PostMapping
    public void agregarNuevoEmpleado(
        @RequestBody Empleado empleado) {
      this.empleadoService.crearEmpleado(empleado);
    }

    
    @PutMapping(path = "/empleado/{id}")
    public void modificarUsuario(
        @PathVariable("id") Long id,
        @RequestBody Empleado empleado) {
        this.empleadoService.modificarEmpleado(id,empleado);
    }

    //Prueba de modificacion de datos con "save"
    @PutMapping(path = "/empleado/save")
    public void modificarUsuarioConSave(@RequestBody Empleado empleado) {
        this.empleadoService.modificarEmpleadoConSave(empleado);
    }



    @DeleteMapping(path = "/delete/{id}")
    public void eliminarEmpleado(@PathVariable("id") Long id){
        this.empleadoService.eliminarEmpleado(id);
    }      
    

}
