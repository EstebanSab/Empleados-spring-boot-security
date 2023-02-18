package com.empleados.empleadosApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import com.empleados.empleadosApi.model.Legajo;
import com.empleados.empleadosApi.service.*;


@RestController
@RequestMapping("api/legajos")
public class LegajoController {
    
  
    private LegajoService legajoService;  

    @Autowired
    public LegajoController(LegajoService legajoService){
        this.legajoService=legajoService;
    }

    @GetMapping
    public List<Legajo> obtenerLegajos(){
        return this.legajoService.getAllLegajos();
    }

   @PostMapping(path = "/empleado/{id}")
   public void agregarLegajo(
    @RequestBody Legajo legajo,
    @PathVariable("id") Long id){
        this.legajoService.crearLegajo(id,legajo);
   }
}
