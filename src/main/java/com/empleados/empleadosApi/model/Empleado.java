package com.empleados.empleadosApi.model;
import javax.persistence.*;



//Entity nombre del la clase
//Con @Table se mapea la clase por la tabla si el nombre no es igual
@Entity(name="Empleado")
@Table( 
    name="empleado",
    uniqueConstraints = {
            @UniqueConstraint(name = "empleado_id_constraint", columnNames = "id")
    }
)
public class Empleado {
	@Id
	@SequenceGenerator(
		name = "generadorIdEmpleado",
		sequenceName = "EMPLEADO_GENERADOR_ID",
        initialValue=1,
		allocationSize = 1
	)

	@GeneratedValue(
		generator = "generadorIdEmpleado",
		strategy = GenerationType.SEQUENCE)

    @Column(name = "id",
            updatable = false,
            nullable=false, 
            unique=true
    )
	private Long id;

	@Column(name="nombre",
            nullable = false,
            columnDefinition = "TEXT")
	private  String nombre;

	@Column(name="apellido",
            nullable = false,
            columnDefinition = "TEXT")
	private String apellido;

	@Column(name="trabajo",
            nullable = false,
            columnDefinition = "TEXT")
	private  String trabajo;

	@Column(name="password",
            nullable = false,
            columnDefinition = "TEXT")
	private  String password;


	@Column(name="authorities",
            nullable = true,
            columnDefinition = "TEXT")
	private  String authorities;

  




	public Empleado(
        String nombre,
        String apellido,
        String trabajo,
		String password
        ) {
		this.nombre = nombre;
        this.apellido = apellido;
        this.trabajo = trabajo;
		this.password=password;
	}

	public Empleado(
        String nombre,
        String apellido,
        String trabajo,
		String password,
		String authorities
        ) {
		this.nombre = nombre;
        this.apellido = apellido;
        this.trabajo = trabajo;
		this.password=password;
		this.authorities=authorities;
	}


	public Empleado() {}
public String getAuthorities() {
	return authorities;
}

public void setAuthorities(String authorities) {
	this.authorities = this.authorities+","+authorities;
}
public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

	public Long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

    public String getApellido() {
		return this.apellido;
	}

	public String getTrabajo() {
		return this.trabajo;
	}


    public void setId(Long id){
		this.id = id;
	}
    
    public void setNombre(String nuevoNombre) {
        this.nombre=nuevoNombre;
	}

    public void setApellido(String nuevoApellido) {
        this.apellido=nuevoApellido;
	}

	public void setTrabajo(String nuevoTrabajo){
		this.trabajo=nuevoTrabajo;
	}


}
