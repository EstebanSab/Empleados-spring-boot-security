package com.empleados.empleadosApi.dto;



public class AuthCredentialsDto {
 
    private String usuario;
    private String password;

    public AuthCredentialsDto() {
        super();
    }

    public AuthCredentialsDto(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

