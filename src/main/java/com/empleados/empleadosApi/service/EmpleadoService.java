package com.empleados.empleadosApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleados.empleadosApi.model.Empleado;

import com.empleados.empleadosApi.repository.EmpleadoRepository;


@Service
public class EmpleadoService {
    private EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(
        EmpleadoRepository empleadoRepository
        ){
        this.empleadoRepository=empleadoRepository;
    }

    public List<Empleado> getAllEmpleados() {
        return this.empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById(Long id) {
        Optional<Empleado> miEmpleado = this.empleadoRepository.findById(id);
        return miEmpleado.orElse(new Empleado());
    }

    public List<Empleado> getEmpleadoByName(String nombre){
        return this.empleadoRepository.selectEmpleadosWhereNombre(nombre);
    }

    public void crearEmpleado(Empleado empleadoParametro) {
        this.empleadoRepository.save(empleadoParametro);
    }

    public void eliminarEmpleado(Long id) {
        this.empleadoRepository.deleteEmpleadoById(id);
    }

    public void modificarEmpleado(Long id, Empleado mEmpleado) {
        String nombre = mEmpleado.getNombre();
        //String apellido = mEmpleado.getApellido();
        this.empleadoRepository.modificaNombreEmpleadoById(nombre,id);
    }

    //prueba modificar empleado con "save"
    public void modificarEmpleadoConSave(Empleado empleado) {
        this.empleadoRepository.save(empleado);
    }


}