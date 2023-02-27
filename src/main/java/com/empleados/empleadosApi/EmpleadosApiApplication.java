package com.empleados.empleadosApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.empleados.empleadosApi.model.Empleado;
import com.empleados.empleadosApi.service.EmpleadoService;

@SpringBootApplication
public class EmpleadosApiApplication {



	public static void main(String[] args) {
		SpringApplication.run(EmpleadosApiApplication.class, args);
	}

	@Bean
    CommandLineRunner commandLineRunner(
            EmpleadoService empleadoService){
	return args -> {


		empleadoService.crearEmpleado(new Empleado("Juan","Ramirez","Ingeniero","123"));
		empleadoService.crearEmpleado(new Empleado("Juan1","Ramirez1","Ingeniero","123"));
		empleadoService.crearEmpleado(new Empleado("Juan2","Ramirez2","Ingeniero","123"));
		empleadoService.crearEmpleado(new Empleado("Juan3","Ramirez3","Ingeniero","123"));
		empleadoService.crearEmpleado(new Empleado("Juan4","Ramirez4","Ingeniero","123"));
		empleadoService.crearEmpleado(new Empleado("Juan5","Ramirez5","Ingeniero","123"));
		};
	
	}


}
