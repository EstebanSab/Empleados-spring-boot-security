package com.empleados.empleadosApi.dto;

public class EmpleadoDto {
        private Long id;
        private String nombre;
        private String apellido;
        private String trabajo;
        private String authorities;
    
        public EmpleadoDto() {
            super();
        }
    
        public EmpleadoDto(
            Long id, 
            String nombre, 
            String apellido, 
            String trabajo, 
            String authorities) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.trabajo = trabajo;
            this.authorities = authorities;
        }
    
        public Long getId() {
            return this.id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return this.nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return this.apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getTrabajo() {
            return this.trabajo;
        }

        public void setTrabajo(String trabajo) {
            this.trabajo = trabajo;
        }

        public String getAuthorities() {
            return this.authorities;
        }
        
        public void setAuthorities(String authorities) {
            this.authorities = authorities;
        }

    }  

