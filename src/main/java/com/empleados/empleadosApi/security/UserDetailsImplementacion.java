package com.empleados.empleadosApi.security;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.empleados.empleadosApi.model.Empleado;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserDetailsImplementacion implements UserDetails {

    private final Empleado empleado;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority("ADMIN"));
        //return roles;
       return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return empleado.getPassword();
    }

    @Override
    public String getUsername() {
       return this.empleado.getNombre();
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre(){
        return this.empleado.getNombre();
    }
    
}
