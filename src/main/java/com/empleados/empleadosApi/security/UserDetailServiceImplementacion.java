package com.empleados.empleadosApi.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empleados.empleadosApi.model.Empleado;
import com.empleados.empleadosApi.repository.EmpleadoRepository;

@Service
public class UserDetailServiceImplementacion implements UserDetailsService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {  
        List<Empleado> miEmpleado = this.empleadoRepository.selectEmpleadosWhereNombre(userName);
    
        return new UserDetailsImplementacion(miEmpleado.get(0));
    }
    
}
