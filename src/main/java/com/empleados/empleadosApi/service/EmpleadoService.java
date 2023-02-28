package com.empleados.empleadosApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.empleados.empleadosApi.dto.EmpleadoDto;
import com.empleados.empleadosApi.mapper.EmpleadoMapper;
import com.empleados.empleadosApi.model.Empleado;

import com.empleados.empleadosApi.repository.EmpleadoRepository;


@Service
public class EmpleadoService {
    private EmpleadoRepository empleadoRepository;
    private EmpleadoMapper empleadoMapper = new EmpleadoMapper();

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    public EmpleadoService(
        EmpleadoRepository empleadoRepository
        ){
        this.empleadoRepository=empleadoRepository;
    }

    public List<EmpleadoDto> getAllEmpleados() {
        return  this.empleadoMapper
            .manyEmpleadoToEmpleadoDto(
                this.empleadoRepository.findAll()
            );
    }

    public EmpleadoDto getEmpleadoById(Long id) {
        Optional<Empleado> miEmpleado = this.empleadoRepository.findById(id);
        return this.empleadoMapper.empleadoToEmpleadoDto(miEmpleado.get());
        //return miEmpleado.orElse(new Empleado());
    }

    public EmpleadoDto getEmpleadoByName(String nombre){
        return this.empleadoMapper.empleadoToEmpleadoDto((Empleado)this.empleadoRepository.selectEmpleadosWhereNombre(nombre).get(0));
    }

    public void crearEmpleado(Empleado empleadoParametro) {
        empleadoParametro.setPassword(
        passwordEncoder.encode(empleadoParametro.getPassword()));
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