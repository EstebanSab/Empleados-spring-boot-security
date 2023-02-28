package com.empleados.empleadosApi.mapper;

import java.util.ArrayList;
import java.util.List;

import com.empleados.empleadosApi.dto.EmpleadoDto;
import com.empleados.empleadosApi.model.Empleado;

public class EmpleadoMapper {
    
    public Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto){
        return new Empleado(
            empleadoDto.getNombre(),
            empleadoDto.getApellido(),
            empleadoDto.getTrabajo(),
            empleadoDto.getAuthorities()
        );
    }

    public EmpleadoDto empleadoToEmpleadoDto(Empleado empleado){
        return new EmpleadoDto(
            empleado.getId(),
            empleado.getNombre(),
            empleado.getApellido(),
            empleado.getTrabajo(),
            empleado.getAuthorities()
        );
    }

    public List<EmpleadoDto> manyEmpleadoToEmpleadoDto(List<Empleado> empleado){
        
        List<EmpleadoDto> empleadosDto = new ArrayList<>();
        for (Empleado empleado2 : empleado) {
           empleadosDto.add(empleadoToEmpleadoDto(empleado2));     
        }

        return empleadosDto;
      
        
    }
}
