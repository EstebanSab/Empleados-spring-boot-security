package com.empleados.empleadosApi.security;


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
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Long idEmpleado = Long.parseLong(id);  
        Empleado miEmpleado = this.empleadoRepository.getById(idEmpleado);
    
        return new UserDetailsImplementacion(miEmpleado);
    }
    
}
